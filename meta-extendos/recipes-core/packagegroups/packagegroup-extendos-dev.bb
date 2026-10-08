SUMMARY = "ExtendOS dev extras"
DESCRIPTION = "Debug and DRM test tools. Never lands on the prod image."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    libdrm-tests \
    superbird-fbpaint \
    kmscube \
    \
    bash \
    coreutils \
    util-linux \
    iproute2 \
    \
    vim-tiny \
    htop \
    strace \
    tcpdump \
    \
    i2c-tools \
    evtest \
    \
    fastfetch \
"
