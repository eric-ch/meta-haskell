DESCRIPTION = "Client-side HTTP programming in Haskell."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f82822417706cf89f5faf41f1e79fe94"

HPN = "HTTP"
inherit hackage

SRC_URI[md5sum] = "ddf23d46c4d6dc0048c454e3d01caa57"
SRC_URI[sha256sum] = "c3b7f8815f5528c88a41a4d62c4a0586edde644f83ae514f036a1cb35ce97026"

DEPENDS += " \
    hkg-mtl \
    hkg-network \
    hkg-parsec \
"

PR = "r1"
