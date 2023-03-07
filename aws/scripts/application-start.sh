#!/bin/bash

# Change to the directory containing the JAR file
cd /home/ec2-user/app

# Start the Java process in the background
nohup java -jar java-test-junior-developer-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &

# Wait for the Java process to start up
sleep 10