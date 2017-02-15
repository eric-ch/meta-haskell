DEPENDS += "ghc gmp-compat"
RDEPENDS_${PN}_class-target += "ghc-runtime"
RDEPENDS_${PN}_class-native += "ghc-native"

PACKAGES = " \
    ${PN} \
    ${PN}-doc \
    ${PN}-staticdev \
    ${PN}-dbg \
    ${PN}-dev \
"
FILES_${PN} = " \
    ${libdir}/${BPN}-${PV}/ghc-*/libH*.so \
    ${libdir}/ghc-*/package.conf.d/*.conf \
"
FILES_${PN}-doc = " \
    ${datadir}/* \
"
FILES_${PN}-staticdev = " \
    ${libdir}/${BPN}-${PV}/ghc-*/libHS*.a \
"
FILES_${PN}-dbg = " \
    ${libdir}/${BPN}-${PV}/ghc-*/*.o \
"
FILES_${PN}-dev = " \
    ${libdir}/${BPN}-${PV}/ghc-*/* \
"

RUNGHC = "runghc"
# Use a local copy of the database to keep control over what is in the sysroot.
GHC_PACKAGE_DATABASE = "local-packages.db"
export GHC_PACKAGE_PATH = "${S}/${GHC_PACKAGE_DATABASE}"

do_update_local_pkg_database() {
    cd ${S} > /dev/null
    # Build the local package database for runghc to process dependencies.
    rm -rf "${GHC_PACKAGE_DATABASE}"
    ghc-pkg init "${GHC_PACKAGE_DATABASE}"
}
addtask do_update_local_pkg_database before do_configure after do_patch
# Ensure that populate_staging is done for every item in DEPENDS before we
# update the local package database. runghc will not be able to process
# dependencies otherwise.
do_update_local_pkg_database[deptask] = "do_populate_sysroot"

do_update_local_pkg_database_append_class-target() {
    ghc_version=$(ghc-pkg --version)
    ghc_version=${ghc_version##* }
    for pkgconf in ${STAGING_LIBDIR}/ghc-${ghc_version}/package.conf.d/*.conf; do
        sed -e "s| /usr/lib| ${STAGING_LIBDIR}|" \
            -e "s| /usr/include| ${STAGING_INCDIR}|" \
            $pkgconf | \
        ghc-pkg -f "${GHC_PACKAGE_DATABASE}" --force update -
    done
    ghc-pkg -f "${GHC_PACKAGE_DATABASE}" recache
}
do_update_local_pkg_database_append_class-native() {
    ghc_version=$(ghc-pkg --version)
    ghc_version=${ghc_version##* }
    for pkgconf in ${STAGING_LIBDIR_NATIVE}/ghc-${ghc_version}/package.conf.d/*.conf; do
        sed -e "s| /usr/lib| ${STAGING_LIBDIR_NATIVE}|" \
            -e "s| /usr/include| ${STAGING_INCDIR_NATIVE}|" \
            $pkgconf | \
        ghc-pkg -f "${GHC_PACKAGE_DATABASE}" --force update -
    done
    ghc-pkg -f "${GHC_PACKAGE_DATABASE}" recache
}

# This is oddly required because there is no good way to pass ${CC} as set
# per bitbake to runghc. One might think of using --ghc-options="-pgmc ${CC%%
# *} -otpc ${CC#* }" but no... it does not manage to parse the options correctly...
do_makeup_wrappers() {
    pushd ${S} > /dev/null
    cat << EOF > ghc-cpp
#!/bin/sh
exec ${CPP} ${CPPFLAGS} "\$@"
EOF
    chmod +x ghc-cpp
    cat << EOF > ghc-cc
#!/bin/sh
exec ${CC} ${CFLAGS} "\$@"
EOF
    chmod +x ghc-cc
# ghc will pass -Wl options, so using gcc not ld.
    cat << EOF > ghc-ld
#!/bin/sh
echo ${CCLD} ${LDFLAGS} "\$@"
exec ${CCLD} ${LDFLAGS} "\$@"
EOF
    chmod +x ghc-ld
    popd > /dev/null
}
addtask do_makeup_wrappers before do_configure after do_patch

do_configure() {
    pushd ${S} > /dev/null
    ${RUNGHC} Setup.*hs clean --verbose
    # TODO: Setup.hs || Setup.lhs
    ${RUNGHC} Setup.*hs configure \
        --package-db="${GHC_PACKAGE_DATABASE}" \
        --ghc-options='-pgmP ./ghc-cpp
                       -pgmc ./ghc-cc
                       -pgml ./ghc-ld' \
        --with-gcc="./ghc-cc" \
        --enable-shared \
        --prefix="${D}${prefix}" \
        --verbose
    popd > /dev/null
}

do_compile() {
    pushd ${S} > /dev/null
    ${RUNGHC} Setup.*hs build \
        --ghc-options='-pgmP ./ghc-cpp
                       -pgmc ./ghc-cc
                       -pgml ./ghc-ld' \
        --with-gcc="./ghc-cc" \
        --verbose
    popd > /dev/null
}

do_local_package_conf() {
    pushd ${S} > /dev/null
    ${RUNGHC} Setup.*hs register \
        --gen-pkg-conf \
        --verbose
    sed -i -e "s| ${D}${prefix}| ${prefix}|" ${S}/${BPN}-${PV}*.conf
    popd > /dev/null
}
addtask do_local_package_conf before do_install after do_compile

do_install() {
    pushd ${S} > /dev/null
    ${RUNGHC} Setup.*hs install --verbose

    # Prepare GHC package database files.
    ghc_version=$(ghc-pkg --version)
    ghc_version=${ghc_version##* }
    install -m 755 -d ${D}${libdir}/ghc-${ghc_version}/package.conf.d
    install -m 644 ${S}/${BPN}-${PV}*.conf ${D}${libdir}/ghc-${ghc_version}/package.conf.d
    popd > /dev/null
}
