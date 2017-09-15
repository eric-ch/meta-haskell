# Some Haskell packages will require case-sensitive name handling (e.g.
# hackage), but bitbake will not handle that. So these should define HPN.
HPN ?= "${PN}"
HPV ?= "${PV}"

SECTION = "devel/haskell"

DEPENDS += " \
    ghc \
    ghc-native \
"

PACKAGES = " \
    ${PN} \
    ${PN}-doc \
    ${PN}-staticdev \
    ${PN}-dbg \
    ${PN}-dev \
"
FILES_${PN} =+ " \
    ${libdir}/${HPN}-${HPV}/ghc-*/libH*.so \
    ${libdir}/ghc-*/package.conf.d/*.conf \
    ${bindir}/* \
"
FILES_${PN}-doc =+ " \
    ${datadir}/* \
"
FILES_${PN}-staticdev =+ " \
    ${libdir}/${HPN}-${HPV}/ghc-*/libHS*.a \
"
FILES_${PN}-dbg =+ " \
    ${libdir}/${HPN}-${HPV}/ghc-*/*.o \
    ${libdir}/${HPN}-${HPV}/ghc-*/.debug \
    ${prefix}/src/debug \
"
FILES_${PN}-dev =+ " \
    ${libdir}/${HPN}-${HPV}/ghc-*/* \
"

RUNGHC = "runghc"
# Use a local copy of the database to keep control over what is in the sysroot.
GHC_PACKAGE_DATABASE = "local-packages.db"
export GHC_PACKAGE_PATH = "${S}/${GHC_PACKAGE_DATABASE}"

do_update_local_pkg_database() {
    # Build the local package database for runghc to process dependencies.
    rm -rf "${GHC_PACKAGE_DATABASE}"
    ghc-pkg init "${GHC_PACKAGE_DATABASE}"
}
addtask do_update_local_pkg_database before do_configure after do_prepare_recipe_sysroot
do_update_local_pkg_database[doc] = "Put together a local Haskell package database for runghc to use, and amend configuration to match bitbake environment."
# Ensure that the recipe's sysroot is populated by every item in DEPENDS before
# we update the local package database. runghc will not be able to process
# dependencies otherwise, neither will ghc-pkg be there if not installed on the
# host.

do_update_local_pkg_database_append_class-target() {
    ghc_version=$(ghc-pkg --version)
    ghc_version=${ghc_version##* }
    for pkgconf in ${STAGING_LIBDIR}/ghc-${ghc_version}/package.conf.d/*.conf; do
        if [ -f "${pkgconf}" ]; then
            sed -e "s| /usr/lib| ${STAGING_LIBDIR}|" \
                -e "s| /usr/include| ${STAGING_INCDIR}|" \
                $pkgconf | \
            ghc-pkg -f "${GHC_PACKAGE_DATABASE}" --force update -
        fi
    done
    ghc-pkg -f "${GHC_PACKAGE_DATABASE}" recache
}
do_update_local_pkg_database_append_class-native() {
    ghc_version=$(ghc-pkg --version)
    ghc_version=${ghc_version##* }
    for pkgconf in ${STAGING_LIBDIR_NATIVE}/ghc-${ghc_version}/package.conf.d/*.conf; do
        if [ -f "${pkgconf}" ]; then
            sed -e "s| /usr/lib| ${STAGING_LIBDIR_NATIVE}|" \
                -e "s| /usr/include| ${STAGING_INCDIR_NATIVE}|" \
                $pkgconf | \
            ghc-pkg -f "${GHC_PACKAGE_DATABASE}" --force update -
        fi
    done
    ghc-pkg -f "${GHC_PACKAGE_DATABASE}" recache
}

# This is oddly required because there is no good way to pass ${CC} as set
# per bitbake to runghc. One might think of using --ghc-options="-pgmc ${CC%%
# *} -otpc ${CC#* }" but no... it does not manage to parse the options correctly...
do_makeup_wrappers() {
    cat << EOF > ghc-cc
#!/bin/sh
exec ${CC} ${CFLAGS} "\$@"
EOF
    chmod +x ghc-cc
# ghc will pass -Wl options, so using gcc (CCLD) not ld.
    cat << EOF > ghc-ld
#!/bin/sh
exec ${CCLD} ${LDFLAGS} "\$@"
EOF
    chmod +x ghc-ld
}
addtask do_makeup_wrappers before do_configure after do_patch
do_makeup_wrappers[doc] = "Generate local wrappers for the compiler to pass bitbake environment through ghc."

do_configure() {
    ${RUNGHC} Setup.*hs clean --verbose
    ${RUNGHC} Setup.*hs configure \
        ${EXTRA_CABAL_CONF} \
        --package-db="${GHC_PACKAGE_DATABASE}" \
        --ghc-options='-dynload deploy
                       -pgmc ./ghc-cc
                       -pgml ./ghc-ld' \
        --with-gcc="./ghc-cc" \
        --enable-shared \
        --prefix="${D}${prefix}" \
        --verbose
}

do_compile() {
    ${RUNGHC} Setup.*hs build \
        --ghc-options='-dynload deploy
                       -pgmc ./ghc-cc
                       -pgml ./ghc-ld' \
        --with-gcc="./ghc-cc" \
        --verbose
}

do_local_package_conf() {
    ${RUNGHC} Setup.*hs register \
        --gen-pkg-conf \
        --verbose
    if [ -f "${S}/${HPN}-${HPV}.conf" ]; then
        sed -i -e "s| ${D}${prefix}| ${prefix}|" ${S}/${HPN}-${HPV}.conf
    fi
}
addtask do_local_package_conf before do_install after do_compile
do_local_package_conf[doc] = "Generate Haskell package configuration."

do_install() {
    ${RUNGHC} Setup.*hs install --verbose

    # Prepare GHC package database files.
    if [ -f "${S}/${HPN}-${HPV}.conf" ]; then
        ghc_version=$(ghc-pkg --version)
        ghc_version=${ghc_version##* }
        install -m 755 -d ${D}${libdir}/ghc-${ghc_version}/package.conf.d
        install -m 644 ${S}/${HPN}-${HPV}.conf ${D}${libdir}/ghc-${ghc_version}/package.conf.d
    fi
}
