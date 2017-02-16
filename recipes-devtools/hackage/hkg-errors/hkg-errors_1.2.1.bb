DESCRIPTION = "This library encourages an error-handling style that directly uses the type system, rather than out-of-band exceptions."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=de760811a0a688464f4e84b2d717c9b9"

SRC_URI[md5sum] = "8e2b27994ae503609b42bb19316bfa1b"
SRC_URI[sha256sum] = "2d693c0df0393faaa27040c9c9b7246c8efece3bb4e814de8854eac79af491d1"

FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"
SRC_URI =+ " \
    file://errors-cheat-cabal.patch \
"

inherit hackage

PR = "r1"
