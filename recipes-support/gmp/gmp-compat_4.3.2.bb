SUMMARY = "The GNU Multiple Precision Arithmetic Library"
DESCRIPTION = "GMP is a free library for arbitrary precision arithmetic, operating on signed integers, rational numbers, and floating-point numbers. There is no practical limit to the precision except the ones implied by the available memory in the machine GMP runs on. GMP has a rich set of functions, and the functions have a regular interface."
HOMEPAGE = "https://gmplib.org/"
LICENSE = "LGPLv2.1+ & GPLv2"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING.LIB;md5=6a6a8e020838b23406c81b19c1d46df6 \
"
SECTION = "devel"

FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"
SRC_URI = "https://gmplib.org/download/gmp/archive/gmp-${PV}.tar.bz2 \
           file://autoconf-am-c-prototypes.patch \
"
SRC_URI[md5sum] = "dd60683d7057917e34630b4a787932e8"
SRC_URI[sha256sum] = "936162c0312886c21581002b79932829aa048cfaf9937c6265aeaa14f1cd1775"

BBCLASSEXTEND = "native"

DEPENDS = "gmp"

# This is to be deployed alongside libgmp. With autonaming it is bound to
# conflict (reverse-runtime for example).
AUTO_LIBNAME_PKGS = ""

inherit autotools

S = "${WORKDIR}/gmp-${PV}"

do_install_append() {
# Remove files conflicting with gmp>4
    rm -rf ${D}/${datadir}
    rm -rf ${D}/${libdir}/gmp
    rm -rf ${D}/${includedir}
    rm -rf ${D}/${libdir}/libgmp.{a,so{,.10},la}
    rm -rf ${D}/${libdir}/libgmpxx.{a,so{,.4},la}
}
do_packagedata() {
# Silence!
    :
}
