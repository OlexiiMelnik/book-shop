# 📕 book-shop 📚
# Project description 👀
"The 'Book Shop' project is a web application for an online book store that allows users to browse, search, and purchase books online. This application is designed to provide a simple and convenient way for users to make book purchases from various authors, genres, and using various filters."

**Key Features**:

• **Search and Book Filtering**: Users can easily find books using various filters such as genre, author, price, and keywords in the book's title. A convenient search system allows for quickly finding books based on various criteria.

• **Book Details**:  Each book has its own page with a detailed description, images, and other information. This allows customers to learn more about the book before making a purchase.

• **Cart and Order Placement**:  The application automatically calculates the total cost of the order based on the added books and provides the option to place an order with just one click.

• **Order Status Tracking**:  Users can track the status of their orders in their personal account. They will receive updates regarding the status of their orders, such as confirmation, shipment, and delivery.

• **User Authentication and Profile**:  Users can create accounts and log in to the system to store their personal information and purchase history.

• **Security Based on Spring Security**:  The application ensures a high level of security using the Spring Security framework, guaranteeing the protection of user data and personal information.

• **JWT Token for Authentication**:  The application utilizes JWT Tokens for secure user authentication and authorization, providing a convenient and reliable security system.

# Business value📈
The "Book Shop" project delivers significant business value by offering the following benefits:

1.**Increased Sales and Revenue**: Thanks to a convenient online platform for purchasing books, the "Book Shop" project can boost sales volumes and revenue. It allows customers to easily browse and buy books, increasing the potential for larger and more frequent transactions.

2.**Enhanced User Experience**: A user-friendly interface and detailed book descriptions enhance the overall user experience. This enables users to find the information they need for informed book purchases, increasing satisfaction with the book-buying process.

3.**Efficient Inventory Management**: The system assists in efficient book inventory management by tracking book availability and automatically updating stock levels. This helps avoid overselling or understocking, optimizing inventory control and reducing potential revenue loss.

4.**Marketing and Promotion**: The online platform provides opportunities for targeted marketing and promotional campaigns. Businesses can run advertising campaigns, offer discounts, and implement loyalty programs to attract and retain customers, increasing brand visibility and customer loyalty.

5.**Data-Driven Insights**: The "Book Shop" project collects valuable data on customer preferences, purchasing patterns, and popular book genres. This data can be analyzed to gain insights into customer behavior and provide opportunities for tailoring offerings and marketing strategies for better results.

# PROJECT STRUCTURE🛠
The project follows a 4-tier architecture

**Data Access Tier**:

• **repository (directory)**: Repositories responsible for data access and interaction with the database.

• **specification (directory)**: Specifications used to create complex queries to the database for data filtering.

**liquibase (directory)**: Configuration and files related to Liquibase used for table creation and data population.

**Presentation Layer**:

• **controller (directory)**: Controllers that handle HTTP requests from users and process them.

• **dto (directory)**: Objects for data transfer (DTOs) used for data exchange between the client and server.

• **validation (directory)**: Rules and checks for data validation.

**Business Logic Layer**:

• **model (directory)**: Models representing the core entities of your application (e.g., books, authors, categories, etc.).

• **service (directory)**: Services responsible for executing the business logic of the application and interacting with repositories.

• **mapper (directory)**: Mappers responsible for transforming objects between different representations.

• **exception (directory)**: Classes for handling exceptions and errors that occur at the business logic and data levels.

• **MyGlobalExceptionHandler**: A global exception handler responsible for handling various types of exceptions at the presentation layer.
• **config (directory)**: Configuration files, such as MapperConfig and SecurityConfig.

**Security Tier**:

• **security (directory)**: A package responsible for ensuring the security of the application, including authentication and authorization.

# Technologies

1.Spring boot

2. Java 17

3. Spring Security

4. Maven

5. RESTfull api

6. Swagger

7. JSON Web Tokens (JWT)

8. Liquibase

9. MapStruct

10. Database: H2 Database and MySQL database

11. Validation

12. Design patterns

13. N-tier architecture

14. Checkstyle
