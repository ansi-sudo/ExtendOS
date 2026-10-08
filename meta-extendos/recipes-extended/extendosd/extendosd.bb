SUMMARY = "ExtendOS app"
DESCRIPTION = "The ExtendOS native app."
LICENSE = "CLOSED"

SRC_URI = " \
    git://github.com/ansi-sudo/extendosd.git;protocol=https;branch=main \
    file://extendosd.service \
"
SRCREV = "1d2c9aa1129d71d7a858b50c4d167cf36b6569e8"

inherit cargo systemd

SYSTEMD_SERVICE:${PN} = "extendosd.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/extendosd.service ${D}${systemd_system_unitdir}/extendosd.service
}
