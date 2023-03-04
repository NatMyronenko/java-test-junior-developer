#!/bin/bash
set -xe


# Copy war file from S3 bucket to tomcat webapp folder
aws s3 cp s3://java-junior-backend-artefacts/java-test-junior-developer.jar /usr/local/tomcat9/webapps/java-test-junior-developer.jar


# Ensure the ownership permissions are correct.
chown -R tomcat:tomcat /usr/local/tomcat9/webapps