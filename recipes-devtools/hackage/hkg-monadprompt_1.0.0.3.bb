DESCRIPTION = "Prompting monad that allows splitting the description of a computation from the implementation of the effects used in that computation."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=18f444a4a5b120cd95f51003afaae2a7"

HPN = "MonadPrompt"
inherit hackage

SRC_URI[md5sum] = "2168e8448aa4b0325a58cb20549670fd"
SRC_URI[sha256sum] = "c26ddd1ea4c732c2e403fee8c18e4ebad868430f2afc350c612766a9a2dfda6c"

DEPENDS += "hkg-mtl"

PR = "r1"
