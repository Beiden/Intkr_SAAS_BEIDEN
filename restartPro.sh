#!/bin/bash

directory=$(cd "$(dirname "$0")"; pwd)

echo "dir : ${directory}"

pid=$(ps x | grep $directory | grep -v grep | awk '{print $1}')

echo $pid

if [ -n "$pid" ]; then
	echo 'stop'
	echo "kill $pid"
	kill $pid
	
	checkCount=0
	pidNew=$(ps x | grep $directory | grep -v grep | awk '{print $1}')
	while [ "$pid" = "$pidNew" ]; do
	    echo "wait a seconds , shutdown app gracefully !"
	    sleep 1 
	    pidNew=$(ps x | grep $directory | grep -v grep | awk '{print $1}')
	    checkCount=$((checkCount + 1))
	    
	    if [ "$checkCount" = "30" ]; then
	    	kill -9 $pid
	    fi
	done
fi

echo 'going to start the app !'

# export MAVEN_OPTS="-Xms128m -Xmx256m"

export MAVEN_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=logs -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:logs/gc.log "

mkdir logs

touch logs/out.log

mvn clean -U -Ddirectory=$directory -Pproduct jetty:run > logs/out.log &
