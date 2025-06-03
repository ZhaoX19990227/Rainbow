#!/bin/bash
echo "Current directory: $(pwd)"
echo "Starting Rainbow application..."

# 设置 Java 17 路径
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-17.0.9.9-1.el7_9.x86_64
export PATH=$JAVA_HOME/bin:$PATH

# 检查 Java 版本
JAVA_VERSION=$($JAVA_HOME/bin/java -version 2>&1 | awk -F '"' '/version/ {print $2}')
echo "Current Java version: $JAVA_VERSION"

cd /opt/rainbow
echo "Changed to directory: $(pwd)"
echo "Listing directory contents:"
ls -la

if [ -f rainbow-0.0.1-SNAPSHOT.jar ]; then
    echo "JAR file found, starting application..."
    nohup $JAVA_HOME/bin/java -jar rainbow-0.0.1-SNAPSHOT.jar > rainbow.log 2>&1 &
    echo "Application started, process ID: $!"
    echo "Checking if log file was created:"
    ls -l rainbow.log
else
    echo "Error: JAR file not found!"
    exit 1
fi 