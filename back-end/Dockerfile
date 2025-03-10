# Step 1: Build Stage
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Step 2: Runtime Stage
FROM openjdk:17-jdk-slim
WORKDIR /app

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# 빌드 결과물을 최종 이미지에 복사
COPY --from=builder /app/build/libs/bangjwo-0.0.1-SNAPSHOT.jar app.jar

# wait-for-it.sh 복사 & 실행 권한
COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

# entrypoint.sh 복사 & 실행 권한
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh

# 실제 실행될 명령은 entrypoint.sh
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
