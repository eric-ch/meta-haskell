DEPENDS += "ghc gmp-compat"
RDEPENDS_${PN} += "ghc-runtime"

RUNGHC = "runghc"
GHC_PACKAGE_DATABASE = "local-packages.db"

do_update_local_pkg_database() {
    # Build the local package database for runghc to process dependencies.
    # TODO: It is kind of inefficient to rebuild it for each package.
    #       That should probably be stored somewhere and updated, but it
    #       is not trivial (race-cond, etc).
    pushd ${S} > /dev/null
    rm -rf "${GHC_PACKAGE_DATABASE}"
    ghc_version=$(ghc-pkg --version)
    ghc_version=${ghc_version##* }
    ghc-pkg init "${GHC_PACKAGE_DATABASE}"
    for pkgconf in ${STAGING_LIBDIR}/ghc-${ghc_version}/package.conf.d/*.conf; do
# TODO: This sed is not necessary as the compiler is installed correctly.
        sed -e "s| /usr/lib|${STAGING_LIBDIR}|" \
            -e "s| /usr/include|${STAGING_INCDIR}|" \
            $pkgconf | \
        ghc-pkg --force -f "${GHC_PACKAGE_DATABASE}" update -
    done
}
addtask do_update_local_pkg_database before do_configure after do_patch

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
exec ${CCLD} ${LDFLAGS} "\$@"
EOF
    chmod +x ghc-ld
    popd > /dev/null
}
addtask do_makeup_wrappers before do_configure after do_patch

do_configure() {
    pushd ${S} > /dev/null
    ${RUNGHC} Setup.*hs clean
    # TODO: Setup.hs || Setup.lhs
    ${RUNGHC} Setup.*hs configure \
        --package-db="${GHC_PACKAGE_DATABASE}" \
        --ghc-options='-pgmP ./ghc-cpp
                       -pgmc ./ghc-cc
                       -pgml ./ghc-ld' \
        --with-gcc="./ghc-cc" \
        --enable-shared \
        --prefix="${prefix}"
    popd > /dev/null
}

do_compile() {
    ${RUNGHC} Setup.*hs build \
        --ghc-options='-pgmP ./ghc-cpp
                       -pgmc ./ghc-cc
                       -pgml ./ghc-ld' \
        --with-gcc="./ghc-cc" \
        --verbose
}
