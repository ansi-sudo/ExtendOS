SUMMARY = "ExtendOS USB HID consumer-control gadget function"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://extendos-usb-hid file://extendos-hid.conf"

do_install() {
    install -d ${D}${libexecdir}
    install -m 0755 ${UNPACKDIR}/extendos-usb-hid ${D}${libexecdir}/extendos-usb-hid
    install -d ${D}${systemd_system_unitdir}/superbird-usb-gadget.service.d
    install -m 0644 ${UNPACKDIR}/extendos-hid.conf ${D}${systemd_system_unitdir}/superbird-usb-gadget.service.d/extendos-hid.conf
}

FILES:${PN} += "${libexecdir} ${systemd_system_unitdir}"
RDEPENDS:${PN} = "bash"
