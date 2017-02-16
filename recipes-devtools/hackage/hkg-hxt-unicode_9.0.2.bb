DESCRIPTION = "Unicode encoding and decoding functions for utf8, iso-latin-* and somes other encodings, used in the Haskell XML Toolbox."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cb61534369f1bbb7cbe62e014d3d5717"

SRC_URI[md5sum] = "f18a4c873cd059eab06953694c54c31a"
SRC_URI[sha256sum] = "ad4132d081cc5a39b67927ca3ad725300f42758cd10783aa7a5b2d20510a23e6"
DEPENDS += "hkg-hxt-charproperties"

inherit hackage

PR = "r1"
