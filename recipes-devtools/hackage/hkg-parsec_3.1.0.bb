DESCRIPTION = "Parsec is designed from scratch as an industrial-strength parser library. It is simple, safe, well documented (on the package homepage), has extensive libraries, good error messages, and is fast. It is defined as a monad transformer that can be stacked on arbitrary monads, and it is also parametric in the input stream type."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4b36c87a94916f61cc02d0d4211317ff"

SRC_URI[md5sum] = "310bf233dcf8ec678c427b1198700b53"
SRC_URI[sha256sum] = "962d39944bae18b0fea60961c77513f455f95c0f67ae4b10ab15484a27b6fb98"
DEPENDS += "hkg-mtl"

inherit hackage

PR = "r1"
