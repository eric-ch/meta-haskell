DESCRIPTION = "This library provides a wrapper to mmap(2) or MapViewOfFile, allowing files or devices to be lazily loaded into memory as strict or lazy ByteStrings, ForeignPtrs or plain Ptrs, using the virtual memory subsystem to do on-demand loading."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b6f78c0dc98ecf779025d3732ac47c3"

inherit hackage

SRC_URI[md5sum] = "1f74b3f59617332b06e277f5212423c1"
SRC_URI[sha256sum] = "d73c683a0e835b29ecc5a55c9d342b454f0b46eeb71955fe1c408d5d67ea0838"

PR = "r1"
