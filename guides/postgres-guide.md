# PostgreSQL Guide

## Introduction
This guide covers the steps needed to set up a PostgreSQL database for a Spring Boot application. Follow along to get your database up and running.

## Prerequisites
- You should have PostgreSQL installed on your system.
- Your Spring Boot application should have the necessary [dependencies](https://mvnrepository.com/artifact/org.postgresql/postgresql) for PostgreSQL integration.

## Installation Steps

### 1. Install PostgreSQL
If you haven't already, download and install PostgreSQL from the [official website](https://www.postgresql.org/download/).

### 2. Login to PostgreSQL
Open the PostgreSQL SQL Shell (psql) or any other PostgreSQL client you prefer.

In PostgreSQL SQL Shell, you will be prompted to enter:
  - **Server**: The server where the PostgreSQL database is running, usually localhost if the database is on your local machine.
  - **Database**: The name of the database you wish to connect to. If you haven't created a new database yet, you can use the default, usually postgres.
  - **Port**: The port number PostgreSQL is listening to, which is 5432 by default.
  - **Username**: The username you will use to access PostgreSQL. This is often set to postgres by default.
  - **Password**:  The password associated with the username you have entered. Enter this to proceed.

### 3. Create Database
Run the following SQL command to create a new database:
```sql
CREATE DATABASE "dino-auction";
```

### Spring Boot Configuration

 1. In your `pom.xml`, make sure you have the PostgreSQL dependency.
 2. Update `application.yml` or `application.properties`.
 3. Run Your Spring Boot Application.


