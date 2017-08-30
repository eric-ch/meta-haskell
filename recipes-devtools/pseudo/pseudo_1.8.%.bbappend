FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"
SRC_URI += " \
    file://use-python2.patch \
"
NO32LIBS = "0"
