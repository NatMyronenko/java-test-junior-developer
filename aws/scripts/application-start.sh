#!/bin/bash

# Change to the directory containing the JAR file
cd /home/ec2-user/app

# Fetch DB password from AWS SSM
DB_PASSWORD=$(aws ssm get-parameters --region eu-central-1 --names DB_PASSWORD --with-decryption --query Parameters[0].Value)
DB_PASSWORD=`echo $DB_PASSWORD | sed -e 's/^"//' -e 's/"$//'`
export DB_PASSWORD=$DB_PASSWORD

# Start the Java process in the background
nohup java -jar java-test-junior-developer-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &

# Wait for the Java process to start up
sleep 10