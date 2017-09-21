inherit cross
require ghc-common.inc

DEPENDS += " \
    ghc-bootstrap-native \
"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = " \
    https://downloads.haskell.org/~ghc/6.12.3/ghc-6.12.3-src.tar.bz2 \
    file://build.mk \
    file://gcc-4.9-fomit-frame-pointer.patch \
    file://use-order-only-constraints-for-directories.patch \
"
SRC_URI[md5sum] = "4c2663c2eff833d7b9f39ef770eefbd6"
SRC_URI[sha256sum] = "6cbdbe415011f2c7d15e4d850758d8d393f70617b88cb3237d2c602bb60e5e68"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7cb08deb79c4385547f57d6bb2864e0f"

S = "${WORKDIR}/ghc-${PV}"

# Use a local copy of the database to amend localities provided in the recipe's
# sysroot.
do_make_ghc_pkg_db() {
    ghc-pkg recache
}
addtask do_make_ghc_pkg_db before do_configure after do_prepare_recipe_sysroot
do_make_ghc_pkg_db[doc] = "Amend the paths in the package database, local to recipe's sysroot."

do_configure() {
    # TODO: That CFLAGS should be determined dynamicaly based on
    # HOST_ARCH/TARGET_ARCH.
    CFLAGS=-m32 ./configure --prefix=${prefix}
    echo "STANDARD_OPTS += \"-I${STAGING_INCDIR_NATIVE}\"" >> rts/ghc.mk
}
# This makes me sad...
#PARALLEL_MAKE = ""
CFLAGS_append = "-Wno-unused -std=gnu98"
do_compile() {
    cp ${WORKDIR}/build.mk ${S}/mk/
    oe_runmake DESTDIR="${D}"
}

do_install() {
    oe_runmake install DESTDIR="${D}"
}
