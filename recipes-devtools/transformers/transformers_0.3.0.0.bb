DESCRIPTION = "A portable library of functor and monad transformers, inspired by the paper "Functional Programming with Overloading and Higher-Order Polymorphism", by Mark P Jones, in Advanced School of Functional Programming, 1995"
HOMEPAGE = "https://hackage.haskell.org/package/transformers"
LICENSE = "GHCL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=315290737f6293f698ed37113aa1226d"
SECTION = "devel/haskell"

SRC_URI = "http://hackage.haskell.org/package/${BPN}-${PV}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "852dc0b79cc2bcb39136287d3dd385e5"
SRC_URI[sha256sum] = "e5aa0c1ec97ad98c389e78f59aca25ab028980a7068a3e585e39564662739591"

BBCLASSEXTEND = "native"

inherit ghc-pkg

S = "${WORKDIR}/${BPN}-${PV}"
PR = "r1"
