# Project Management

A Java Backend application for project management.

## Description

Project Management is a simple application built on the Java and Spring Boot platform. The application provides basic features to manage individual or organizational projects.

## ERD Diagram
[Project Design](https://drive.google.com/file/d/1QIW9Akdt6hKP1Oe9twGT9sVH1lyzSkzY/view?usp=sharing)

## Installation Guide

### Requirements

- Java 17
- Maven
- MySQL

### Installation and Running

1. Clone the repository:

    ```bash
    git clone https://github.com/Norzax/Project_Management.git
    ```

2. Configure the database in `application.properties`.

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

## Important Branches

- **master**: Main branch, always stable, containing released versions.
- **develop**: Development branch, used for new development, incomplete features.
- **a/feature(setup)/<ordinal-feature(setup)>/<developer>/<feature(setup)-name>**: Branch for each feature, specific setup.
- **b/update/<ordinal-feature(setup)>.<version>/<developer>/<feature(setup)-name>**: Branch for each feature, specific setup being developed.
- **c/fix/<ordinal-feature(setup)>.<fixed-version>/<developer>/<feature(setup)-name>**: Branch for each feature, specific setup with fixed issues.
- **#/hotfix/<hotfix-name>**: Branch for quickly fixing urgent production issues.

## Directory Structure

- `/src`: Java source code.
- `/src/main/resources`: Application configuration and resources.
- `/pom.xml`: Maven configuration file.

## Contribution

We warmly welcome contributions from the community. If you'd like to contribute to the project, please [contact via email](mailto:giangbaoluan5@.com)

---
© 2023 GiangBaoLuan Project-Management.
