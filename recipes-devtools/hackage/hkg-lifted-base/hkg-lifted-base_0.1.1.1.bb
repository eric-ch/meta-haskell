DESCRIPTION = "Lifted-base exports IO operations from the base library lifted to any instance of MonadBase or MonadBaseControl."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=67634a94867d2b4cea9192052bca1335"

SRC_URI[md5sum] = "f642df016e04eea4ad494bbaabed2bdd"
SRC_URI[sha256sum] = "e0445a9a67341236b2b852bb627cca67d5b79a770f04c2b7cbd9432c821dd3b1"

FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"
SRC_URI =+ " \
    file://lifted-base-cabal-cheat.patch \
"

DEPENDS += " \
    hkg-base-unicode-symbols \
    hkg-monad-control \
    hkg-transformers-base \
"

inherit hackage

PR = "r1"
