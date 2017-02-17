DESCRIPTION = "In mathematics, a semigroup is an algebraic structure consisting of a set together with an associative binary operation. A semigroup generalizes a monoid in that there might not exist an identity element. It also (originally) generalized a group (a monoid with all inverses) to a type where every element did not have to have an inverse, thus the name semigroup."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a54c8d25fd04603d961c2a2c3b866355"

inherit hackage

SRC_URI[md5sum] = "f406607e0421554cde40594b65f83086"
SRC_URI[sha256sum] = "ecd8b368215fbd697f6508dcac77b153ac02b9a1568694bdc886d998091d833c"

FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"
SRC_URI =+ " \
    file://semigroups-cabal-cheat.patch \
    file://semigroups-derive-data-typeable.patch \
"

PR = "r1"
