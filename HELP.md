# Help Guide

This guide provides the necessary steps to install and run the MrCoffee application.

## Prerequisites
Before running the MrCoffee application, ensure that you have the following prerequisites:

- Java Development Kit (JDK) - Version 20 or later.
- Apache Maven - Build and dependency management tool.
- Apache ActiveMQ - JMS provider for message queuing and JMS integration.

## Installation and Setup
Follow these steps to install and set up the MrCoffee application:

1. Download and install Apache ActiveMQ from the official website (https://activemq.apache.org/).
2. Start the Apache ActiveMQ server by opening the command prompt and navigating to the 'bin' folder in the activemq folder.
   Then open the correct folder for your system and run:
         
       activemq.bat start

3. Clone the MrCoffee project from the GitHub repository: https://github.com/juninatt/mrcoffee.
4. Open a terminal or command prompt and navigate to the project directory.
5. Build the project using Maven by running the command: mvn clean install.
6. Start the application by running the command: mvn spring-boot:run.

## Accessing the Application
To access the application, use the following credentials:

Username: admin
Password: admin

After successful login, you will be able to access all available endpoints.

## API Documentation
Once logged in, a link to the Swagger API documentation will be available. 
The API documentation provides detailed information about the available endpoints, request/response formats, and authentication requirements.

The Swagger UI will display an interactive documentation page where you can explore and test the API endpoints.

