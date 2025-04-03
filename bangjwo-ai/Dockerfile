FROM python:3.9-slim

WORKDIR /app

# 필수 패키지 설치 (크롬 실행을 위해)
RUN apt-get update && apt-get install -y \
    wget gnupg unzip curl fonts-liberation libasound2 libatk1.0-0 \
    libatk-bridge2.0-0 libc6 libcairo2 libcups2 libdbus-1-3 libexpat1 \
    libfontconfig1 libgcc1 libgdk-pixbuf2.0-0 libglib2.0-0 libgtk-3-0 \
    libnspr4 libnss3 libpango-1.0-0 libpangocairo-1.0-0 libstdc++6 \
    libx11-6 libx11-xcb1 libxcb1 libxcomposite1 libxcursor1 libxdamage1 \
    libxext6 libxfixes3 libxi6 libxrandr2 libxrender1 libxss1 libxtst6 \
    lsb-release xdg-utils && \
    rm -rf /var/lib/apt/lists/*

# Chrome 설치
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list && \
    apt-get update && apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# requirements 설치
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# 소스 복사
COPY . .

# law_db 폴더 미리 생성
RUN mkdir -p /app/law_db

CMD ["uvicorn", "app:app", "--host", "0.0.0.0", "--port", "5000"]



