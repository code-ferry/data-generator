#!/bin/bash

APP_NAME=data-generator
APP_VERSION=1.0.0
LOG_MAX_SIZE=50MB
XMX_SIZE=1024M

# change directory to program dir
FWDIR="$(cd `dirname $0`/..; pwd)"
cd ${FWDIR}

if [ ! -d ${FWDIR}/java.pid ]; then
    touch ${FWDIR}/java.pid
fi

OSUSER=$(id -nu)
PSNUM=$(cat ${FWDIR}/java.pid)
if [[ "$PSNUM" -ne "" ]]; then
    echo ${APP_NAME}" has been started! stop first."
    exit;
fi

java -Xmx${XMX_SIZE} -Dapp.name=${APP_NAME} -Dmax.size=${LOG_MAX_SIZE} -Duser.dir=${FWDIR} -jar ${APP_NAME}-${APP_VERSION}.jar &
echo $! > ${FWDIR}/java.pid
exit;
