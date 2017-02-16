DESCRIPTION = "This package defines the type class MonadBaseControl, a subset of MonadBase into which generic control operations such as catch can be lifted from IO or any other base monad. Instances are based on monad transformers in MonadTransControl, which includes all standard monad transformers in the transformers library except ContT."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=67634a94867d2b4cea9192052bca1335"

SRC_URI[md5sum] = "ea20f9fa1d65db2ae60afeb8954b547e"
SRC_URI[sha256sum] = "c17fd5fa094044816ab79158a00fa7a9fd8b35dfea27e1eecc4d4049b3916c57"
DEPENDS += " \
    hkg-base-unicode-symbols \
    hkg-transformers \
    hkg-transformers-base \
"

inherit hackage

PR = "r1"
