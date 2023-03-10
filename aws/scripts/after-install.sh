#!/bin/bash

# Download the application jar from S3
aws s3 cp s3://java-junior-backend-artefacts/java-test-junior-developer.jar /home/ec2-user/app/
