@echo off
rem Gradle 빌드 실행
echo "Gradle build start..."
call gradlew clean
if %errorlevel% neq 0 (
    echo "Gradle clean fail."
    exit /b %errorlevel%
)

call gradlew build
if %errorlevel% neq 0 (
    echo "Gradle build fail."
    exit /b %errorlevel%
)

rem Docker Compose로 애플리케이션 실행
echo "Docker Compose start..."
docker compose up
if %errorlevel% neq 0 (
    echo "Docker Compose fail."
    exit /b %errorlevel%
)

echo "Application run whole success."
exit /b 0