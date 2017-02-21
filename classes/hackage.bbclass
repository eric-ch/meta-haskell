HOMEPAGE = "https://hackage.haskell.org/package/${HPN}"

SRC_URI = "http://hackage.haskell.org/package/${HPN}-${PV}/${HPN}-${PV}.tar.gz"

S = "${WORKDIR}/${HPN}-${PV}"

BBCLASSEXTEND = "native"

# Prefix to avoid conflicts with synonyms packages (e.g, curl).  Some recipes
# will have to define HPN since hackage URI are case-sensitive (Apache?), but
# ipk do not handle upper-case package-name.
HPN ?= "${@d.getVar("BPN", True).split("hkg-", 1)[1]}"

inherit ghc
