DESCRIPTION = "Provides functions to inspect and manipulate virtual hard disk (VHD) files, according to the VHD specification (version 1.0)."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cc8224b3041a54c20bd7becce249bb02"

SRC_URI[md5sum] = "4d727ca01e55884d642613593edb47f5"
SRC_URI[sha256sum] = "f15dd0127cbaaaa3fef69bfde6e2ac8b83e9d3a0295bf94de8c0c9e8c928e375"
DEPENDS += " \
    hkg-mmap \
    hkg-storable-endian \
    hkg-cereal \
    hkg-text \
"

inherit hackage

PR = "r1"
