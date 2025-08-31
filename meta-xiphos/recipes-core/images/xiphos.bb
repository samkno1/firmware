DESCRIPTION = "Xiphos image"
LICENSE = "CLOSED"

require recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES:append = "\
    empty-root-password \
"

IMAGE_INSTALL:append = "\
    bootgen \
"

IMAGE_FSTYPES += "tar.gz wic.zst wic.bmap"
