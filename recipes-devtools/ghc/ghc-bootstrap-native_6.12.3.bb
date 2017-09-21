inherit native
require ghc-common.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=7cb08deb79c4385547f57d6bb2864e0f"

DEPENDS = " \
    ncurses \
    gmp-compat \
"

SRC_URI = "https://www.haskell.org/ghc/dist/${PV}/ghc-${PV}-i386-unknown-linux-n.tar.bz2"
SRC_URI[md5sum] = "8ed8540571f7b10d8caf782755e35818"
SRC_URI[sha256sum] = "d86c82cd349adfacdbccd7fb67e7218d42b39bb748d36bfeb6ad9f172da629f5"
#SRC_URI = "https://www.haskell.org/ghc/dist/${PV}/ghc-${PV}-x86_64-unknown-linux-n.tar.bz2"
#SRC_URI[md5sum] = "d58e5a50d8b120ac933afbd10a773aef"
#SRC_URI[sha256] = "f0e13bdec040f06b1074595ddc39064f75214aee05d64554f4809dca3e138001"

S = "${WORKDIR}/ghc-${PV}"

COMPATIBLE_HOST = "(x86_64|i.86).*-linux"

do_configure() {
    ./configure --prefix=${STAGING_DIR} \
                --bindir=${STAGING_BINDIR} \
                --libdir=${STAGING_LIBDIR} \
                --datadir=${STAGING_DATADIR}
}

do_compile() {
    :
}

do_install() {
    oe_runmake install "DESTDIR=${D}"
}
do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
