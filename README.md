# MrCoffee Project

The MrCoffee project is a Java-based application that simulates a coffee maker machine with purchase orders, utilizing Spring Boot, Maven, and H2 database. It provides functionalities for managing coffee products, placing orders, processing payments, and generating invoices.

## Features

- Create, update, and delete coffee products.
- Place orders for coffee products with quantity and customer details.
- Process payments for orders.
- Generate invoices for completed orders.
- Retrieve order and customer information.
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

## Prerequisites

Before running the MrCoffee application, ensure that you have the following prerequisites:

- Java Development Kit (JDK) - Version 11 or later.
- Apache Maven - Build and dependency management tool.
- Apache ActiveMQ - JMS provider for message queuing and JMS integration.

### Installation and Setup

Follow these steps to install and set up the MrCoffee application:

1. Download and install Apache ActiveMQ from the official website (https://activemq.apache.org/).
2. Start the Apache ActiveMQ server opening the command prompt and by navigating to the 'bin' folder in the activemq folder.
   Then open the correct folder for your system and run:
          
       activemq.bat start
3.  Clone the MrCoffee project from the GitHub repository (https://github.com/your-username/mrcoffee).
4.  Open a terminal or command prompt and navigate to the project directory.
5.  Build the project using Maven by running the command: mvn clean install.
6.  Start the application by running the command: mvn spring-boot:run.

## Configuration

The MrCoffee application is pre-configured with default settings for the H2 database and JMS integration with Apache ActiveMQ. However, if you need to modify the configuration, you can do so in the application.properties file located in the src/main/resources directory.

Ensure that you update the JMS connection details in the configuration file to match your Apache ActiveMQ installation.