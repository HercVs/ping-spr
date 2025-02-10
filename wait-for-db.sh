#!/bin/sh

echo "Waiting for database to be ready..."
while ! nc -z db 3306; do   
  sleep 5
done

echo "Database is ready. Starting Spring Boot..."
exec java -jar edu-ping.jar