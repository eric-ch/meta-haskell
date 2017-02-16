DESCRIPTION = "Haskell utilities for parsing, filtering, transforming and generating XML documents."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b84b8bea272e7357c5c7fe6f255ba732"

SRC_URI[md5sum] = "9635c348e70c0446e74783e7c267050c"
SRC_URI[sha256sum] = "c32c10b95446ecb938dc6cd34585187efd3fcb4b21f7d0c7cbd646ba94c87516"
DEPENDS += "hkg-polyparse"

HPN = "HaXml"

inherit hackage

PR = "r1"
