#!/bin/bash
set -e  # 스크립트 실행 중 오류 발생 시 즉시 종료

echo "Waiting for MySQL to be up..."
wait-for-it.sh db:3306 --timeout=30 --strict -- echo "MySQL is up!"

echo "Starting the application..."
exec java -jar app.jar
