DESCRIPTION = "This package provides a pure interface for compressing and decompressing streams of data represented as lazy ByteStrings. It uses the zlib C library so it has high performance. It supports the zlib, gzip and raw compression formats."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=38ddb957e5077481b801400d5fe61118"

SRC_URI[md5sum] = "8fb2988b112e38d04a527b892f770507"
SRC_URI[sha256sum] = "382a16d09d47ba9c742d552b80cf3902c81556fc69a8d6983af98b47263545c2"

inherit hackage

PR = "r1"
