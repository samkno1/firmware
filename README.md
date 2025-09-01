# Xiphos yocto image for the RaspberryPi

## Download and install dependencies
Instructions for downloading the required packages for poky can be obtained from:
https://docs.yoctoproject.org/5.0.11/ref-manual/system-requirements.html#required-packages-for-the-build-host
- i.e., On Fedora
```
$ sudo dnf install bzip2 ccache chrpath cpio cpp diffstat diffutils file \
     findutils gawk gcc gcc-c++ git glibc-devel glibc-langpack-en gzip \
     hostname libacl lz4 make patch perl perl-Data-Dumper \
     perl-File-Compare perl-File-Copy perl-FindBin \
     perl-Text-ParseWords perl-Thread-Queue perl-bignum perl-locale \
     python python3 python3-GitPython python3-jinja2 python3-pexpect \
     python3-pip rpcgen socat tar texinfo unzip wget which xz zstd
```

## Fetch sources using Git
```
$ git clone "https://github.com/samkno1/firmware.git"
$ cd firmware/
$ git submodule update --init --force --recursive
```

## Setup environment
From the project directory, firmware/
```
$ cd poky
$ source oe-init-build-env
```
Return to the project directory, firmware/. Add the non-poky layers to bblayers.conf
```
$ bitbake-layers add-layer meta-raspberrypi
$ bitbake-layers add-layer meta-xiphos
```

## Build image
After setting up the bitbake build environment
```
$ MACHINE=raspberrypi5 bitbake xiphos
```

## Prepare the SDCard with the xiphos image
- Install bmap-tools
    i.e., on Fedora:
```
$ sudo dnf install bmap-tools
```
- Go to the raspberry pi images directory
``` 
$ cd poky/build/tmp/deploy/images/raspberrypi5
```
- Ensure the SD Card is not mounted. For example, with an SD card at /dev/sdb
```
$ sudo umount /dev/sdb*
```
- Load image onto the SD card with bmap
```   
$ sudo bmaptool copy --bmap xiphos-raspberrypi5.rootfs.wic.bmap --no-sig-verify \
                        --no-verify xiphos-raspberrypi5.rootfs.wic.zst /dev/sdb
```
