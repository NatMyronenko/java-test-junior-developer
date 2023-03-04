#!/bin/bash

# Change to the directory containing the JAR file
cd /home/ec2-user

# Start the Java process in the background
nohup java -jar /home/ec2-user/java-test-junior-developer.jar > /dev/null 2>&1 &

# Wait for the Java process to start up
sleep 10