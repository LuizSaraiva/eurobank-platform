# Customer Service

Customer management microservice for the EuroBank platform

## Responsibilities

- Register bank customers
- Manage basic customer profile data
- Track KYC status
- Expose customer APIs
- Provide health and readiness endpoint for Kubernetes

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- MySQL 8
- Liquibase
- Spring Actuator
- OpenAPI / Swagger
- Maven
- Docker Compose

## Local Dependencies

This Service depends on MySQL

Start dependencies from the root folder:

```bash
docker compose up -d
```

## Running the Service

From the `customer-service` folder:

```bash
./mvnw spring-boot:run
```

The service will start on:

```text
http://localhost:8081
```

## API Documentation

Swagger UI:

```text
http://localhost:8081/swagger-ui.html
```

## Health Checks

General health:

```text
http://localhost:8081/actuator/health
```

Liveness:

```text
http://localhost:8081/actuator/health/liveness
```

Readiness:

```text
http://localhost:8081/actuator/health/readiness
```

## Architecture

This service follows Hexagonal Architecture:

```text
domain
application
adapter
infrastructure
```

### Domain

Contains business rules and domain models.

### Application

Contains use cases and ports.

### Adapters

Contains inbound adapters such as REST controllers and outbound adapters such as persistence.

### Infrastructure

Contains technical configuration such as OpenAPI, logging and exception handling.

## Notes

Hibernate DDL auto-generation is disabled for schema creation.

Database schema changes must be managed through Liquibase.