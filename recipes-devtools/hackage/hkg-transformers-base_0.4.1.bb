DESCRIPTION = "This package provides a straightforward port of monadLib's BaseM typeclass to transformers."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=93e7d84c230d8f835ebffaa82a621e39"

SRC_URI[md5sum] = "bd99282e2daae5eecd1c953b7b77c990"
SRC_URI[sha256sum] = "4fa9e8ae38f0ed0633251f7a18c51946ee9fa486ed768389b5b6edaacf3c7cb4"
DEPENDS += "hkg-transformers"

inherit hackage

PR = "r1"
