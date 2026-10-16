SUMMARY = "ExtendOS app"
DESCRIPTION = "The ExtendOS native app."
LICENSE = "CLOSED"

SRC_URI = " \
    git://github.com/ansi-sudo/extendosd.git;protocol=https;branch=main \
    file://extendosd.service \
"
SRCREV = "e2824f5b99481f9b5167299ab250f5815dc78e85"

require ${BPN}-crates.inc

inherit cargo cargo-update-recipe-crates systemd

SYSTEMD_SERVICE:${PN} = "extendosd.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/extendosd.service ${D}${systemd_system_unitdir}/extendosd.service
}
