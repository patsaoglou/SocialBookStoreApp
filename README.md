# Online Social Bookstore Application

Welcome to the Online Social Bookstore project! This application is designed to allow individuals to exchange used books for free. Users can create accounts, offer books, request books, and manage their book lists through an intuitive and user-friendly interface.

![home](https://github.com/patsaoglou/SocialBookStoreAPP/assets/93339707/f9d98aec-70a7-4adf-8c8d-af3a453c60b2)

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Getting Started](#getting-started)
- [Development Process](#development-process)
- [Requirements](#requirements)
  - [Functional Requirements](#functional-requirements)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Technical Requirements](#technical-requirements)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

The objective of this project is to develop an online social bookstore application that facilitates the free exchange of used books among users. Users can create profiles, offer books they no longer need, request books they are interested in, and interact with other users to arrange book exchanges.

![Book Offer Details](https://github.com/patsaoglou/SocialBookStoreAPP/assets/93339707/84a6171f-3cf7-462f-88a7-81c41b646ea8)

## Features

- **User Management:** Create accounts, log in, and manage profiles.
- **Book Offering:** Add books to personal offer lists with details like title, author, category, and summary.
- **Book Requests:** Search for and request books from other users.
- **Book Recommendations:** Receive book recommendations based on user preferences.
- **User Interaction:** Contact other users to arrange book exchanges.
  
![register](https://github.com/patsaoglou/SocialBookStoreAPP/assets/93339707/e18718f6-4544-4e7f-a112-16db9f50093a)

## Development Process

This project follows the Scrum development process. The team plans and executes a series of sprints, implementing user stories from the project backlog and validating them with appropriate tests. The final deliverables include the project implementation, sprint reports, and detailed design documentation.

## Requirements

### Functional Requirements

1. **User Management:**
   - Create a new account
   - Log in and log out
   - Create and manage user profiles

2. **Book Offering:**
   - Add books to personal offer lists
   - Browse and manage book requests
   - Notify users about the availability of requested books

3. **Book Requests:**
   - Search for books by title and author
   - Make requests for books found in search results
   - Browse recommended book offers
  
![myrequests](https://github.com/patsaoglou/SocialBookStoreAPP/assets/93339707/cd31c3d5-7fd9-4c36-b27c-2ed685bb1f78)

### Non-Functional Requirements

1. **Maintainability:**
   - Employ an architecture with low coupling and high cohesion.
   - Use design patterns for extensibility and maintainability (e.g., Strategy pattern).

2. **Usability:**
   - Provide a simple and user-intuitive interface.
   - Include user guidelines for main functionalities.

### Technical Requirements

- **Languages and Frameworks:**
  - Java
  - Spring Boot

- **Database:**
  - MySQL

- **Testing:**
  - JUnit

- **Development Tools:**
  - Eclipse or IntelliJ IDEA

## Design Information

### Architecture

The application is designed to promote low coupling and high cohesion. It follows the principles of Fowler's enterprise application architecture patterns, ensuring a clear separation between different parts of the application, such as views, controllers, domain model, and database. This separation facilitates the mapping of the domain model to the underlying database schema.

### Extensibility and Maintainability

To ensure the application is easily extendable and maintainable, it is designed with the following considerations:

1. **Book Recommendation Strategies:**
   - **Category-Based Recommendation:** Recommends books that belong to the categories specified in the user's profile.
   - **Author-Based Recommendation:** Recommends books written by the user's preferred authors, as specified in the user's profile.
   - **Composite Recommendation:** Combines the first two strategies, recommending books written by preferred authors and belonging to preferred categories.

2. **Search Strategies:**
   - **Exact Search:** Produces a list of books with titles and authors that exactly match the query.
   - **Approximate Search:** Produces a list of books where the title contains the query and the authors include at least one of the specified authors.
   
![search](https://github.com/patsaoglou/SocialBookStoreAPP/assets/93339707/c7c5577b-1718-4a0a-96a5-472870766c5f)

### Design Patterns

The application utilizes well-known design patterns to achieve the required flexibility and maintainability:

- **Strategy Pattern:** Used to implement interchangeable book recommendation and search strategies.
- **Template Method Pattern:** Ensures that similar but slightly different search and recommendation strategies share common code, reducing duplication.

## Getting Started

To set up the development environment for the Online Social Bookstore application, follow these steps:

### Install Java

Ensure that you have Java installed on your system. You can download and install Java from the [official Oracle website](https://www.oracle.com/java/technologies/javase-downloads.html).

### Set Up Spring Boot

Spring Boot is used to develop the backend of the application. Follow the official [Spring Boot documentation](https://spring.io/projects/spring-boot) to set up Spring Boot on your development environment.

### Install MySQL

MySQL is used as the database for the application. You can download and install MySQL from the [official MySQL website](https://www.mysql.com/downloads/).

### Configure the Database

1. **Create a MySQL Database:**
   Create a MySQL database to store the data for the Online Social Bookstore application.

2. **Update Database Connection Settings:**
   Open the `application.properties` file located in `src/main/resources/` directory of the project.
   Update the database connection settings in this file to match your MySQL database configuration.
   Example:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password

## Contributing

We welcome contributions to the Online Social Bookstore project! If you're interested in contributing, please fork the repository and submit a pull request. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Thank you for checking out the Online Social Bookstore project! We hope this application helps foster a community of book lovers who can share and enjoy books together.
