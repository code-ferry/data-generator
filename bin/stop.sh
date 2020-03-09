#!/bin/bash

FWDIR="$(cd `dirname $0`/..; pwd)"
PSNUM=$(cat ${FWDIR}/java.pid)

if [[ "$PSNUM" -eq "" ]]; then
    echo "this process not exist!"
    exit;
else
    echo "kill process" ${PSNUM}
    # kill -9 ${PSNUM}
    kill -SIGTERM ${PSNUM}
    echo "" > ${FWDIR}/java.pid
fi
