SUMMARY = "Image booting a xfce desktop"

LICENSE = "MIT"

PV = "V1.0"
PR = "r0"

inherit extrausers

IMAGE_FEATURES += " package-management"

# The following variables can be used to control the image
# update via CURT.
#
# Set _INCLUDE_UBOOT to "1" in order to update the U-Boot.
# Set _INCLUDE_CUSTOM to "1" in order to execute the
# custom.sh script after the update process is ready.
# Set _ONLY_CUSTOM to "1" in order to disable all other
# update tasks and only run the custom.sh script.
# Use _CUSTOM_FILE to specify a path to your custom.sh
# script. Default is ${THISDIR}/custom.sh
# -> meta-ces/recipes/images/custom.sh
# Use _UPDATE_ADD_FILES to include additional files in
# the update archive. May contain multiple files separated
# by whitespaces.
# Set _INCLUDE_CCLIB to "1" in order to override curt
# internal functions. There are some pitfalls you have to
# consider when using this. Please contact us if you want
# to use these functions.
# Use _CCLIB_FILE to specify a path to your cclib.sh
# script. Default is ${THISDIR}/cclib.sh
# -> meta-ces/recipes/images/cclib.sh

CHRIST_UPDATE_INCLUDE_UBOOT = "0"
CHRIST_UPDATE_INCLUDE_CUSTOM = "0"
CHRIST_UPDATE_ONLY_CUSTOM = "0"
# CHRIST_UPDATE_CUSTOM_FILE = "${THISDIR}/custom.sh"
# CHRIST_UPDATE_ADD_FILES = ""
CHRIST_UPDATE_INCLUDE_CCLIB = "0"
# CHRIST_UPDATE_CCLIB_FILE = "${THISDIR}/cclib.sh"

IMAGE_LINGUAS = "de-de en-gb"

SOUND_PKGS_INSTALL = '${@bb.utils.contains("DISTRO_FEATURES", "alsa", "${SOUND_PKGS}", "",d)}'
SOUND_PKGS = " \
    packagegroup-fsl-gstreamer1.0-full \
    alsa-plugins \
    alsa-tools \
    alsa-utils \
"

IMAGE_INSTALL_append = " \
    udev-extra-rules \
    packagegroup-base-extended \
    mmc-utils \
    tmux \
    picocom \
    openssl \
    openssl-misc \
    ca-certificates \
    userconf \
    nano \
    logrotate \
    liberation-fonts \
    tzdata \
    tzdata-misc \
    tzdata-posix \
    tzdata-right \
    tzdata-europe \
    can-utils \
    libsocketcan \
    iproute2 \
    ${SOUND_PKGS_INSTALL} \
    u-boot-fw-utils \
    libusbg \
    e2fsprogs \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    nfs-utils-client \
    openssh-scp \
    openssh-sftp \
    bzip2 \
    grep \
    dosfstools \
    util-linux-fstrim \
    evtest \
    hdparm \
    iperf3 \
    i2c-tools \
    ethtool \
    stress \
    util-linux \
    systemd-bootcheck \
    getting-started \
    enable-update \
    \
    packagegroup-core-boot \
    packagegroup-core-x11 \
    packagegroup-xfce-base \
    kernel-modules \
    thunar-media-tags-plugin \
    thunar-volman \
    xfce4-cpugraph-plugin \
    xfceuserconf \
    \
    htop \
    procps \
    mmc-utils \
    florence3 \
    keyboard-touch \
    pcmanfm \
    gtk-theme-clearlooks \
    systemd-usbmountpoints \
    gedit \
    vim \
    curl \
"

# Copy Licenses to image /usr/share/common-licenses
COPY_LIC_MANIFEST = "1"
COPY_LIC_DIRS = "1"

#create the file /etc/timestamp
IMAGE_POSTPROCESS_COMMAND = "rootfs_update_timestamp"

#create the deployment directory-tree
require recipes/images/christ-image-fstype.inc

DEPENDS_append = " virtual/bootloader"

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "busybox shadow"

SYSTEMD_DEFAULT_TARGET = "graphical.target"

EXTRA_USERS_PARAMS = " usermod -P root123 root; "

export IMAGE_BASENAME = "ces-xfce-demoimage"

inherit image
