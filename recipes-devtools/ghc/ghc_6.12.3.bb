DESCRIPTION = "The Glasgow Haskell Compiler."
HOMEPAGE = "https://www.haskell.org/ghc/download_ghc_6_12_3"
LICENSE = "GHCL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7cb08deb79c4385547f57d6bb2864e0f"
SECTION = "devel/haskell"
DEPENDS = "ncurses gmp-compat"

FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"

SRC_URI = " \
    https://www.haskell.org/${BPN}/dist/${PV}/${BPN}-${PV}-i386-unknown-linux-n.tar.bz2 \
"

SRC_URI[md5sum] = "8ed8540571f7b10d8caf782755e35818"
SRC_URI[sha256sum] = "d86c82cd349adfacdbccd7fb67e7218d42b39bb748d36bfeb6ad9f172da629f5"

# Any i.86 really.
COMPATIBLE_MACHINE_class-target = "(qemux86)"

PR = "r1"

S = "${WORKDIR}/ghc-${PV}"

PACKAGES += " \
    ${PN}-runtime \
    ${PN}-headers \
"

# Lib/bin shipped with the compiler yet required at runtime.
# TODO: integer-simple would be advisable...?
# TODO: utf8-string is not installed?
PRIVATE_LIBS_${PN}-runtime += " \
    libHSrts-ghc6.12.3.so \
    libHSrts_thr-ghc6.12.3.so \
    libHSffi-ghc6.12.3.so \
    libHSarray-0.3.0.0-ghc6.12.3.so \
    libHSbase-3.0.3.2-ghc6.12.3.so \
    libHSbase-4.2.0.2-ghc6.12.3.so \
    libHScontainers-0.3.0.0-ghc6.12.3.so \
    libHStime-1.1.4-ghc6.12.3.so \
    libHSpretty-1.0.1.1-ghc6.12.3.so \
    libHSdirectory-1.0.1.1-ghc6.12.3.so \
    libHSold-locale-1.0.0.2-ghc6.12.3.so \
    libHSsyb-0.1.0.2-ghc6.12.3.so \
    libHSprocess-1.0.1.3-ghc6.12.3.so \
    libHSrandom-1.0.0.2-ghc6.12.3.so \
    libHSfilepath-1.1.0.4-ghc6.12.3.so \
    libHSbytestring-0.9.1.7-ghc6.12.3.so \
    libHSinteger-simple-0.1.0.0-ghc6.12.3.so \
    libHSold-time-1.0.0.5-ghc6.12.3.so \
    libHSutf8-string-0.3.4-ghc6.12.3.so \
    libHSunix-2.4.0.2-ghc6.12.3.so \
    libHSghc-prim-0.2.0.0-ghc6.12.3.so \
    libHShaskell98-1.0.1.1-ghc6.12.3.so \
"
FILES_${PN}-runtime = " \
    ${libdir}/${BPN}-${PV}/libHSrts_debug-${BPN}${PV}.so \
    ${libdir}/${BPN}-${PV}/libHSrts_thr_debug-${BPN}${PV}.so \
    ${libdir}/${BPN}-${PV}/libHSrts-${BPN}${PV}.so \
    ${libdir}/${BPN}-${PV}/libHSrts_thr-${BPN}${PV}.so \
    ${libdir}/${BPN}-${PV}/libHSffi-${BPN}${PV}.so \
    ${libdir}/${BPN}-${PV}/array-*/*.so \
    ${libdir}/${BPN}-${PV}/base-*/*.so \
    ${libdir}/${BPN}-${PV}/containers-*/*.so \
    ${libdir}/${BPN}-${PV}/time-*/*.so \
    ${libdir}/${BPN}-${PV}/pretty-*/*.so \
    ${libdir}/${BPN}-${PV}/directory-*/*.so \
    ${libdir}/${BPN}-${PV}/old-locale-*/*.so \
    ${libdir}/${BPN}-${PV}/syb-*/*.so \
    ${libdir}/${BPN}-${PV}/process-*/*.so \
    ${libdir}/${BPN}-${PV}/random-*/*.so \
    ${libdir}/${BPN}-${PV}/filepath-*/*.so \
    ${libdir}/${BPN}-${PV}/bytestring-*/*.so \
    ${libdir}/${BPN}-${PV}/integer-simple-*/*.so \
    ${libdir}/${BPN}-${PV}/old-time-*/*.so \
    ${libdir}/${BPN}-${PV}/utf8-string-*/*.so \
    ${libdir}/${BPN}-${PV}/unix-*/*.so \
    ${libdir}/${BPN}-${PV}/ghc-prim-*/*.so \
    ${libdir}/${BPN}-${PV}/haskell98-*/*.so \
"
FILES_${PN} = " \
    ${libdir}/${BPN}-${PV}/extensible-exceptions-*/*.so \
    ${libdir}/${BPN}-${PV}/template-haskell-*/*.so \
    ${libdir}/${BPN}-${PV}/dph-seq-*/*.so \
    ${libdir}/${BPN}-${PV}/dph-base-*/*.so \
    ${libdir}/${BPN}-${PV}/ghc-*/*.so \
    ${libdir}/${BPN}-${PV}/integer-gmp-*/*.so \
    ${libdir}/${BPN}-${PV}/ghc-binary-*/*.so \
    ${libdir}/${BPN}-${PV}/bin-package-db-*/*.so \
    ${libdir}/${BPN}-${PV}/dph-prim-par-*/*.so \
    ${libdir}/${BPN}-${PV}/Cabal-*/*.so \
    ${libdir}/${BPN}-${PV}/hpc-*/*.so \
    ${libdir}/${BPN}-${PV}/dph-par-*/*.so \
    ${libdir}/${BPN}-${PV}/dph-prim-interface-*/*.so \
    ${libdir}/${BPN}-${PV}/dph-prim-seq-*/*.so \
    ${bindir}/ghc \
    ${bindir}/ghc-${PV} \
    ${bindir}/ghci \
    ${bindir}/ghci-${PV} \
    ${bindir}/unlit \
    ${bindir}/hp2ps \
    ${bindir}/hsc2hs \
    ${bindir}/hpc \
    ${bindir}/runghc \
    ${bindir}/ghc-pkg \
    ${bindir}/ghc-pkg-${PV} \
    ${bindir}/ghc-split \
    ${bindir}/ghc-asm \
    ${bindir}/haddock \
    ${bindir}/runhaskell \
    ${libdir}/${BPN}-${PV}/ghc \
    ${libdir}/${BPN}-${PV}/unlit \
    ${libdir}/${BPN}-${PV}/hsc2hs \
    ${libdir}/${BPN}-${PV}/runghc \
    ${libdir}/${BPN}-${PV}/ghc-pkg \
    ${libdir}/${BPN}-${PV}/ghc-split \
    ${libdir}/${BPN}-${PV}/ghc-asm \
    ${libdir}/${BPN}-${PV}/haddock \
    ${libdir}/${BPN}-${PV}/extra-gcc-opts \
    ${libdir}/${BPN}-${PV}/package.conf.d/*.conf \
    ${libdir}/${BPN}-${PV}/package.conf.d/*.cache \
"
FILES_${PN}-headers = " \
    ${libdir}/${BPN}-${PV}/*/include \
    ${libdir}/${BPN}-${PV}/include \
    ${libdir}/${BPN}-${PV}/*.h \
"
FILES_${PN}-doc += " \
    ${libdir}/${BPN}-${PV}/html/* \
    ${libdir}/${BPN}-${PV}/*.txt \
"
FILES_${PN}-staticdev += " \
    ${libdir}/${BPN}-${PV}/${BPN}-${PV}/*.a \
    ${libdir}/${BPN}-${PV}/*/*.a \
    ${libdir}/${BPN}-${PV}/*.a \
"
# Catch everything...
FILES_${PN}-dev = " \
    ${libdir}/${BPN}-${PV}/*/*/*/*/*/*/*/*hi \
    ${libdir}/${BPN}-${PV}/*/*/*/*/*/*/*hi \
    ${libdir}/${BPN}-${PV}/*/*/*/*/*/*hi \
    ${libdir}/${BPN}-${PV}/*/*/*/*/*hi \
    ${libdir}/${BPN}-${PV}/*/*/*/*hi \
    ${libdir}/${BPN}-${PV}/*/*/*hi \
    ${libdir}/${BPN}-${PV}/*/*hi \
    ${libdir}/${BPN}-${PV}/*/*.o \
    ${libdir}/${BPN}-${PV}/*.o \
"

RDEPENDS_${PN} = "perl"
RDEPENDS_${PN}-runtime = "gmp-compat"

BBCLASSEXTEND = "native"

COMPATIBLE_HOST = "(x86_64|i.86).*-linux"
do_configure_class-native() {
    CFLAGS=-m32 ./configure --prefix=${STAGING_DIR} \
                            --bindir=${STAGING_BINDIR} \
                            --libdir=${STAGING_LIBDIR} \
                            --datadir=${STAGING_DATADIR}
}
do_configure_class-target() {
    CFLAGS=-m32 ./configure --prefix=${prefix}
}

do_compile() {
    :
}

do_install() {
    oe_runmake install "DESTDIR=${D}"
}
