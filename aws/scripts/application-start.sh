#!/bin/bash

# Change to the directory containing the JAR file
cd /home/ec2-user/app

# Fetch DB_URL from AWS SSM
DB_URL=$(aws ssm get-parameters --region eu-central-1 --names DB_URL --with-decryption --query Parameters[0].Value)
DB_URL=`echo $DB_URL | sed -e 's/^"//' -e 's/"$//'`
export DB_URL=$DB_URL


# Fetch DB_USERNAME from AWS SSM
DB_USERNAME=$(aws ssm get-parameters --region eu-central-1 --names DB_USERNAME --with-decryption --query Parameters[0].Value)
DB_USERNAME=`echo $DB_USERNAME | sed -e 's/^"//' -e 's/"$//'`
export DB_USERNAME=$DB_USERNAME

# Fetch DB_PASSWORD from AWS SSM
DB_PASSWORD=$(aws ssm get-parameters --region eu-central-1 --names DB_PASSWORD --with-decryption --query Parameters[0].Value)
DB_PASSWORD=`echo $DB_PASSWORD | sed -e 's/^"//' -e 's/"$//'`
export DB_PASSWORD=$DB_PASSWORD


# Fetch KEYCLOAK_ID from AWS SSM
KEYCLOAK_ID=$(aws ssm get-parameters --region eu-central-1 --names KEYCLOAK_ID --with-decryption --query Parameters[0].Value)
KEYCLOAK_ID=`echo $KEYCLOAK_ID | sed -e 's/^"//' -e 's/"$//'`
export KEYCLOAK_ID=$KEYCLOAK_ID


# Fetch KEYCLOAK_REALM from AWS SSM
KEYCLOAK_REALM=$(aws ssm get-parameters --region eu-central-1 --names KEYCLOAK_REALM --with-decryption --query Parameters[0].Value)
KEYCLOAK_REALM=`echo $KEYCLOAK_REALM | sed -e 's/^"//' -e 's/"$//'`
export KEYCLOAK_REALM=$KEYCLOAK_REALM


# Fetch KEYCLOAK_SECRET from AWS SSM
KEYCLOAK_SECRET=$(aws ssm get-parameters --region eu-central-1 --names KEYCLOAK_SECRET --with-decryption --query Parameters[0].Value)
KEYCLOAK_SECRET=`echo $KEYCLOAK_SECRET | sed -e 's/^"//' -e 's/"$//'`
export KEYCLOAK_SECRET=$KEYCLOAK_SECRET


# Fetch KEYCLOAK_URL from AWS SSM
KEYCLOAK_URL=$(aws ssm get-parameters --region eu-central-1 --names KEYCLOAK_URL --with-decryption --query Parameters[0].Value)
KEYCLOAK_URL=`echo $KEYCLOAK_URL | sed -e 's/^"//' -e 's/"$//'`
export KEYCLOAK_URL=$KEYCLOAK_URL


# Start the Java process in the background
nohup java -jar java-test-junior-developer-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &

# Wait for the Java process to start up
sleep 10
