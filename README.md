# MrCoffee Project

The MrCoffee project is a Java-based application that simulates a coffee maker machine with purchase orders, utilizing Spring Boot, Maven, and H2 database.
It provides functionalities for managing coffee products, placing orders, processing payments, and generating invoices.

## Features

- Create, update, and delete coffee products.
- Place orders for coffee products with quantity and customer details.
- Process payments for orders.
- Generate invoices for completed orders.
- Retrieve order and customer information.
- User management with role-based access control.
- Supports multiple coffee products and their specific attributes.

## Technologies Used

- Java
- Spring Boot
- Maven
- H2 Database
- JPA (Java Persistence API)
- RESTful API
- MS (Java Message Service)
- Apache ActiveMQ (JMS provider)
- Swagger (API documentation)
- Spring Security

## Getting Started
Please check the [Help Guide](HELP.md) for detailed instructions on how to install and run the application.


## Configuration

The MrCoffee application is pre-configured with default settings for the H2 database and JMS integration with Apache ActiveMQ. 
However, if you need to modify the configuration, you can do so in the application.properties file located in the src/main/resources directory.

Ensure that you update the JMS connection details in the configuration file to match your Apache ActiveMQ installation.

## API Documentation

The MrCoffee application utilizes Swagger for API documentation. 
The API documentation provides detailed information about the available endpoints, request/response formats, and authentication requirements.

To access the API documentation, see [Help Guide](HELP.md).