SUMMARY = "ExtendOS shell aliases"
DESCRIPTION = "Installs /etc/profile.d/extendos-aliases.sh."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://extendos-aliases.sh"

inherit allarch

do_install() {
    install -d ${D}${sysconfdir}/profile.d
    install -m 0644 ${UNPACKDIR}/extendos-aliases.sh ${D}${sysconfdir}/profile.d/extendos-aliases.sh
}

FILES:${PN} = "${sysconfdir}/profile.d/extendos-aliases.sh"
