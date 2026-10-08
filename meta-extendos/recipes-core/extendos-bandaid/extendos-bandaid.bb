SUMMARY = "Bandaid ext4 for the kiosk example"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

BANDAID_VENDOR = "extendos"
BANDAID_PACKAGES = "extendos-daemon extendos-default-webapp"

inherit bandaid-image
