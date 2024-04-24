# CloudFileStorageApi
REST API  that provides users with a place to store files and data.

![Java](https://img.shields.io/badge/java-black?style=for-the-badge&logo=openjdk&link=https%3A%2F%2Fspring.io)
![Spring](https://img.shields.io/badge/spring-black?style=for-the-badge&logo=spring)
![Spring Boot](https://img.shields.io/badge/spring_boot-black?style=for-the-badge&logo=springboot)
![Postgesql](https://img.shields.io/badge/postgresql-black?style=for-the-badge&logo=postgresql)
![Redis](https://img.shields.io/badge/redis-black?style=for-the-badge&logo=redis)
![Docker](https://img.shields.io/badge/docker-black?style=for-the-badge&logo=docker)
![Minio](https://img.shields.io/badge/minio-black?style=for-the-badge&logo=minio)
![Swagger](https://img.shields.io/badge/swagger-black?style=for-the-badge&logo=swagger)
![Maven](https://img.shields.io/badge/Maven-black?style=for-the-badge&logo=apachemaven)

![Stack Diagramm](/docs/stack-diagram.png)

## Get started
1. Clone repository
```shell
git clone https://github.com/IRuuy/CloudFileStorageApi
```
2. In order to run the application, you need to install docker compose and execute the command at project directory:
```shell
docker compose up -d
```

## OpenApi
You can get acquainted with the project documentation, available endpoints and request structure after launching the application using the link `http://your-url/swagger-ui/index.html`.
File - [openapi.json](/src/main/resources/static/openapi/openapi.json).

## Database diagram
![Database diagram](/docs/database-diagram.png)
