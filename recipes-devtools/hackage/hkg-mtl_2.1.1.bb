DESCRIPTION = "Monad classes using functional dependencies, with instances for various monad transformers, inspired by the paper Functional Programming with Overloading and Higher-Order Polymorphism, by Mark P Jones, in Advanced School of Functional Programming, 1995"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=315290737f6293f698ed37113aa1226d"
DEPENDS =+ "hkg-transformers"

SRC_URI[md5sum] = "0654be687f1492a2ff30cf6f3fb7eed0"
SRC_URI[sha256sum] = "9250831796b1678380d915d2953ce94fa466af8d5c92d0c569963f0f0b037a90"

inherit hackage

PR = "r1"
