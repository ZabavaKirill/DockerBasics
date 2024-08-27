Простое веб-приложение для загрузки файлов и хранения информации о них в БД с использованием Dockerfile, Makefile, Volume и docker compose.

localhost:8080/swagger-ui/index.html - Swagger Ui

Запуск без docker compose: 
  - make create-net - создание сети
  - make run-db - запуск контейнера с БД
  - make build - сборка образа приложения
  - make run-app - запуск контейнера приложения

Запуск с docker compose:
  - docker compose up
