DESCRIPTION = "DBus core bindings (obsolete)."
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI[md5sum] = "f635e99147a6ef7b1fa3212c6767fa83"
SRC_URI[sha256sum] = "6da338e54d1cd4bcfbbc12bf6af08f7e90b420cb809f59e8aa94451cd17e08d1"

FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"
SRC_URI =+ " \
    file://expose-customisable-transport.patch \
    file://functor-fix.patch \
"
DEPENDS += " \
    hkg-binary \
    hkg-data-binary-ieee754 \
    hkg-haxml \
    hkg-mtl \
    hkg-network \
    hkg-parsec \
    hkg-text \
"

inherit hackage

PR = "r1"
