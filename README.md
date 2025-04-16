# Running the Project with Docker

This project is configured to run using Docker and Docker Compose. Below are the steps and requirements to set up and run the project:

## Requirements

- Docker version 20.10 or later
- Docker Compose version 1.29 or later

## Environment Variables

The following environment variables are required for the services:

- `POSTGRES_USER`: Username for the PostgreSQL database (default: `user`)
- `POSTGRES_PASSWORD`: Password for the PostgreSQL database (default: `password`)

## Build and Run Instructions

1. Clone the repository and navigate to the project directory.
2. Build and start the services using Docker Compose:

   ```bash
   docker-compose up --build
   ```

3. Access the application at `http://localhost:8080`.

## Exposed Ports

- Application: `8080`
- Database: Not exposed externally

## Notes

- The application is built using Maven and runs on OpenJDK 21.
- The database service uses PostgreSQL with a persistent volume for data storage.

For further details, refer to the Dockerfile and `docker-compose.yml` provided in the project.