#!/bin/bash

# Check if the backup folder exists, if not, create it
if [ ! -d "/home/ec2-user/backup" ]; then
  mkdir /home/ec2-user/backup
  chmod 777 /home/ec2-user/backup
fi

# Check if the last_work folder exists, if not, create it
if [ ! -d "/home/ec2-user/last_work" ]; then
  mkdir /home/ec2-user/last_work
  chmod 777 /home/ec2-user/last_work
fi

# Copy the new jar file to the last_work folder
cp /home/ec2-user/app/java-test-junior-developer-0.0.1-SNAPSHOT.jar /home/ec2-user/last_work/


# Copy the existing jar file to the backup folder
cp /home/ec2-user/app/java-test-junior-developer-0.0.1-SNAPSHOT.jar /home/ec2-user/backup/java-test-junior-developer-0.0.1-SNAPSHOT_$(date +"%Y%m%d_%H%M%S").jar


# Copy the new jar file from S3 to the application folder
aws s3 cp s3://java-junior-backend-artefacts/java-test-junior-developer-0.0.1-SNAPSHOT.jar /home/ec2-user/app/


echo "File copied successfully"