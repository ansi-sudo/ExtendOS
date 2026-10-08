SUMMARY = "ExtendOS core runtime"
DESCRIPTION = "BSP runtime + Mesa + the ExtendOS app."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    packagegroup-superbird-runtime \
    \
    extendos-daemon \
    extendos-default-webapp \
    extendosd \
    \
    mesa \
"
