#!/bin/bash

# Wait for the application to start up
sleep 10

# Check the application health status
response=$(curl --write-out '%{http_code}' --silent --output /dev/null -X POST http://localhost:8080/actuator/shutdown)

if [ $response -ne 200 ]; then
  echo "Application stop failed"
  exit 1
fi

echo "Application stopped succeeded"