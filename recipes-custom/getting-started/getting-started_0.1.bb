SUMMARY = "Getting Started Information for the Customer."
DESCRIPTION = "This package provides some html pages that contain basic information about the system."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://footer.png \
           file://header-logo-left.png \
           file://index.html \
           file://style.css \
           file://header-logo-center.png \
           file://header-logo-right.png \
           file://script.js \
          "

S = "${WORKDIR}/"

FILESEXTRAPATHS_prepend = "${THISDIR}/files:"

do_install () {

	# install the html pages
	install -d -m 0755 ${D}/home/root/www
	install -m 0644 ${WORKDIR}/footer.png ${D}/home/root/www/footer.png
	install -m 0644 ${WORKDIR}/header-logo-center.png ${D}/home/root/www/header-logo-center.png
	install -m 0644 ${WORKDIR}/header-logo-left.png ${D}/home/root/www/header-logo-left.png
	install -m 0644 ${WORKDIR}/header-logo-right.png ${D}/home/root/www/header-logo-right.png
	install -m 0644 ${WORKDIR}/index.html ${D}/home/root/www/index.html
	install -m 0644 ${WORKDIR}/script.js ${D}/home/root/www/script.js
	install -m 0644 ${WORKDIR}/style.css ${D}/home/root/www/style.css
}

FILES_${PN} += " \
            /home/root/www/* \
         "

# Prevents do_package failures with:
# debugsources.list: No such file or directory:
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
