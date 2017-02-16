DESCRIPTION = "The Haskell XML Toolbox bases on the ideas of HaXml and HXML, but introduces a more general approach for processing XML with Haskell."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cb61534369f1bbb7cbe62e014d3d5717"

SRC_URI[md5sum] = "1e21e439c24181136ac7a77ed4c64267"
SRC_URI[sha256sum] = "d9e8d0b82d64775a1529d3747adfe820852a743e386ce75b080b040cf5959045"
DEPENDS += " \
    hkg-binary \
    hkg-deepseq \
    hkg-hunit \
    hkg-hxt-charproperties \
    hkg-hxt-regex-xmlschema \
    hkg-hxt-unicode \
    hkg-mtl \
    hkg-network \
    hkg-parsec \
"

inherit hackage

PR = "r1"
