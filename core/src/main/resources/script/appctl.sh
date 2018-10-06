#!/bin/sh
CUR_DIR=$(cd "$(dirname "$0")";pwd)
source ${CUR_DIR}/config.sh

function start(){
    JAVA_CMD="java -Dkill=${CUR_DIR} ${JAVA_PROS} -jar ${CUR_DIR}/$(ls *.jar) $@ "
    echo "${JAVA_CMD}"
    nohup ${JAVA_CMD} > ${CUR_DIR}/out 2>&1 &
    echo $! > ${CUR_DIR}/pid
    echo "start success,pid=$(cat ${CUR_DIR}/pid)"
}

function stop(){
    PS_CMD="ps ux|grep kill=${CUR_DIR} | grep -v grep | tr -s ' ' ' '|cut -f2 -d\ "
    PID=$(eval "${PS_CMD}")
    until [ "" == "${PID}" ]
    do
        kill ${PID}
        sleep 1
        if [[ $(ps ${PID}|wc -l) -gt 1 ]]; then
            kill -9 ${PID}
        fi
        echo "kill "${PID}"!"
        PID=$(eval "${PS_CMD}")
    done
    echo "shut down success!"
}

function usage(){
    echo "error: the first argument must be one of <start|stop|restart>"
    exit -1
}

if [ "$#" -lt "1" ];then
    usage
fi

cd ${CUR_DIR}

case "$1" in
    start)
        shift
        start $@
    ;;
    stop)
        shift
        stop
    ;;
    restart)
        shift
        stop
        start $@
    ;;
    *)
        usage
    ;;
esac

