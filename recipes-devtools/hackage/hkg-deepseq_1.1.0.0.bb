SUMMARY = "Provides methods for fully evaluating data structures ("deep evaluation")."
DESCRIPTION = "Provides methods for fully evaluating data structures ("deep evaluation"). Deep evaluation is often used for adding strictness to a program, e.g. in order to force pending exceptions, remove space leaks, or force lazy I/O to happen. It is also useful in parallel programs, to ensure pending work does not migrate to the wrong thread."
LICENSE = "GHCL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a83ef7d4aeff27103dc2d40e9a70c2e6"

SRC_URI[md5sum] = "41194f8633be8e30cacad88146dbf7c2"
SRC_URI[sha256sum] = "947c45e7ee862159f190fb8e905c1328f7672cb9e6bf3abd1d207bbcf1eee50a"

inherit hackage

PR = "r1"
