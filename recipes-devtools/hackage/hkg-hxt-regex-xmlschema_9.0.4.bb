DESCRIPTION = "This library supports full W3C XML Schema regular expressions inclusive all Unicode character sets and blocks."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cb61534369f1bbb7cbe62e014d3d5717"

inherit hackage

SRC_URI[md5sum] = "1ff0c995ebaedaaf1289e6e65e60857c"
SRC_URI[sha256sum] = "eef1c9ae42b3d2ed78019bec6bed92034b1395ea5aa725a459df9ca5d34d884a"

DEPENDS += " \
    hkg-parsec \
    hkg-hxt-charproperties \
"

PR = "r1"
