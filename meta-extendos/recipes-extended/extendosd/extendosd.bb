SUMMARY = "ExtendOS app"
DESCRIPTION = "The ExtendOS native app."
LICENSE = "CLOSED"

SRC_URI = " \
    git://github.com/ansi-sudo/extendosd.git;protocol=https;branch=main \
    file://extendosd.service \
"
SRCREV = "b9bf7d8c3f113c43710c0278bf2c4762c334b0dd"

require ${BPN}-crates.inc

inherit cargo cargo-update-recipe-crates systemd

SYSTEMD_SERVICE:${PN} = "extendosd.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/extendosd.service ${D}${systemd_system_unitdir}/extendosd.service
}
