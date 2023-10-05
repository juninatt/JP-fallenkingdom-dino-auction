# DinoAuction Project

The DinoAuction project is a Java-based application inspired by Jurassic Park: Fallen Kingdom,
that simulates a dinosaur auction system with transaction orders. The project is developed using Spring Boot, Maven, and supports both H2 and PostgreSQL databases.

## Technologies Used

- Java
- Spring Boot
- Maven
- H2 Database
- PostgreSQL
- JPA (Java Persistence API)
- RESTful API
- MS (Java Message Service)
- Apache ActiveMQ (JMS provider)
- Swagger (API documentation)
- Spring Security

## Getting Started
Please check the [Help Guide](guides/HELP.md) for detailed instructions on how to install and run the application.

### Database Configuration

The project comes pre-configured to work with an H2 database. However, it can also be configured to use a PostgreSQL database. Please refer to the PostgresSQL [Guide](guides/postgres-guide.md) for step-by-step instructions on setting up and configuring PostgreSQL for this application.

> **Note**: If you prefer to continue using the H2 database, you can do so by making sure the corresponding settings are active in the `application.yml` file located in the `src/main/resources` directory.

## Configuration

The DinoAuction application is pre-configured with default settings for the H2 database and JMS integration with Apache ActiveMQ. 
However, if you need to modify the configuration, you can do so in the application.yml file located in the src/main/resources directory.

Ensure that you update the JMS connection details in the configuration file to match your Apache ActiveMQ installation.

## API Documentation

The DinoAuction application utilizes Swagger for API documentation. 
The API documentation provides detailed information about the available endpoints, request/response formats, and authentication requirements.

To access the API documentation, see [Help Guide](guides/HELP.md).