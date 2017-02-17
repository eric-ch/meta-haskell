DESCRIPTION = "An either monad transformer."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=83c1add4bf1a48b9eb934561b5fb178c"

inherit hackage

SRC_URI[md5sum] = "e9e5b415af8acd9e7ecf79adf973ba09"
SRC_URI[sha256sum] = "0f520338a8d075a6cabe3bdeb73e3602110a3bc7dcfe5833140524867bbf79e8"
DEPENDS += " \
    hkg-semigroupoids \
    hkg-semigroups \
    hkg-transformers \
"

PR = "r1"
