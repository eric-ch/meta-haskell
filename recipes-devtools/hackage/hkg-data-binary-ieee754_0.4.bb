DESCRIPTION = "Parser/Serialiser for IEEE-754 Float and Decimal values to/from raw octets."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

inherit hackage

SRC_URI[md5sum] = "15028bb912463eb4bede1c454c0edddc"
SRC_URI[sha256sum] = "01d5619f6e84587b380e467b4b6df012059b8cd6a3d8526d328b8d33e50ccce7"

DEPENDS += "hkg-binary"

PR = "r1"
