# Repsy Package Repo API

This is a minimalistic REST API to deploy and download `.rep` packages (similar to jar files) for the fictional "Repsy" programming language.

## Features

- Upload and store `.rep` files + metadata (`meta.json`)
- Download files using a simple REST API
- Pluggable storage: file-system or object-storage (MinIO)
- PostgreSQL-based metadata persistence

## Tech Stack

- Java 17 + Spring Boot
- PostgreSQL
- MinIO (S3 compatible)
- Docker + Docker Compose

## Endpoints

### ➕ Deploy Package

`POST /{packageName}/{version}`  
**Form fields:**

- `package`: `.rep` file (binary zip file)
- `meta`: `meta.json`

### 📥 Download File

`GET /{packageName}/{version}/{fileName}`  
Returns `meta.json` or `package.rep` file.

## Run with Docker

```bash
mvn clean package -DskipTests
docker-compose up --build
```
