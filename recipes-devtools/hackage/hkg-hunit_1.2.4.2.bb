DESCRIPTION = "HUnit is a unit testing framework for Haskell, inspired by the JUnit tool for Java, see: http://www.junit.org."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4d036bff24e7f9e1a7a9012fbe91bb35"

HPN = "HUnit"
inherit hackage

SRC_URI[md5sum] = "b834809dc5c4ac4d1e519d8d6c456600"
SRC_URI[sha256sum] = "fdbcbc28196d616b449bf3e46c30dcd5577dc92f290458e2dbdbfc5a65de327a"

PR = "r1"

RDEPENDS_${HPN} += "libc6"
