# REST Assured Java API Automation Framework

A comprehensive, modular API automation framework built with Gradle, TestNG, RestAssured, and Allure Reports for testing the FakeStore API.

## 🚀 Features

- **Modular Architecture**: Clean separation of concerns with dedicated packages for endpoints, POJOs, utilities, and tests
- **POJO Layers**: Request → Response → Module → Wrapper pattern for robust data handling
- **Comprehensive Test Coverage**: CRUD operations for Products, Users, and Carts endpoints
- **Allure Reporting**: Beautiful, detailed test execution reports with step-by-step documentation
- **Parallel Execution**: TestNG parallel test execution for faster test runs
- **Fluent Assertions**: AssertJ for readable and maintainable assertions
- **Lombok Integration**: Reduced boilerplate code with automatic getters, setters, and builders

## 📁 Project Structure

```
src
├── main
│   └── java
│       └── api
│           ├── base
│           │   └── BaseTest.java           # Base URI, request/response specs
│           ├── endpoints
│           │   ├── ProductsAPI.java        # Products endpoint methods
│           │   ├── UsersAPI.java           # Users endpoint methods
│           │   └── CartsAPI.java           # Carts endpoint methods
│           ├── pojos
│           │   ├── request
│           │   │   ├── ProductRequest.java
│           │   │   ├── UserRequest.java
│           │   │   └── CartRequest.java
│           │   ├── response
│           │   │   ├── ProductResponse.java
│           │   │   ├── UserResponse.java
│           │   │   └── CartResponse.java
│           │   ├── module
│           │   │   └── ProductModule.java
│           │   └── wrappers
│           │       └── ProductWrapper.java
│           └── utils
│               └── JsonUtils.java          # JSON serialization/deserialization
└── test
    ├── java
    │   └── tests
    │       ├── ProductsTests.java          # Product API tests
    │       ├── UsersTests.java             # User API tests
    │       └── CartsTests.java             # Cart API tests
    └── resources
        ├── testng.xml                      # TestNG configuration
        └── allure.properties               # Allure configuration
```

## 🛠️ Tech Stack

- **Java 11+**
- **Gradle 8.x** - Build automation
- **TestNG 7.8.0** - Test framework
- **RestAssured 5.3.2** - API testing library
- **Jackson 2.15.3** - JSON processing
- **Allure 2.24.0** - Test reporting
- **Lombok 1.18.30** - Code generation
- **AssertJ 3.24.2** - Fluent assertions
- **SLF4J 2.0.9** - Logging

## 📋 Prerequisites

- Java 11 or higher
- Gradle 7.x or higher
- Allure CLI (for generating reports)

### Install Allure CLI

**macOS:**
```bash
brew install allure
```

**Windows:**
```bash
scoop install allure
```

**Linux:**
```bash
sudo apt-add-repository ppa:qameta/allure
sudo apt-get update
sudo apt-get install allure
```

## 🚦 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd restassured-java-api-automation
```

### 2. Build the Project
```bash
./gradlew clean build
```

### 3. Run Tests
```bash
./gradlew test
```

### 4. Generate Allure Report
```bash
./gradlew allureReport
```

### 5. View Allure Report
```bash
allure serve build/allure-results
```

Or use the Gradle task:
```bash
./gradlew allureServe
```

## 📊 Test Execution

### Run All Tests
```bash
./gradlew test
```

### Run Specific Test Class
```bash
./gradlew test --tests tests.ProductsTests
```

### Run Tests with Parallel Execution
Tests are configured to run in parallel by default (3 threads). Modify `testng.xml` to adjust parallelism.

## 📝 API Endpoints Covered

### Products API
- GET /products - Get all products
- GET /products/{id} - Get product by ID
- POST /products - Create new product
- PUT /products/{id} - Update product
- PATCH /products/{id} - Partially update product
- DELETE /products/{id} - Delete product
- GET /products/categories - Get all categories
- GET /products/category/{category} - Get products by category

### Users API
- GET /users - Get all users
- GET /users/{id} - Get user by ID
- POST /users - Create new user
- PUT /users/{id} - Update user
- DELETE /users/{id} - Delete user

### Carts API
- GET /carts - Get all carts
- GET /carts/{id} - Get cart by ID
- GET /carts/user/{userId} - Get user's carts
- POST /carts - Create new cart
- PUT /carts/{id} - Update cart
- DELETE /carts/{id} - Delete cart


## 🎯 Best Practices Implemented

1. **Page Object Model (POM)** - Endpoint classes encapsulate API calls
2. **Data-Driven Testing** - POJOs for request/response handling
3. **Reusable Components** - Base test class with common configurations
4. **Meaningful Test Names** - Descriptive test method names
5. **Comprehensive Assertions** - Multiple validation points per test
6. **Allure Annotations** - @Step, @Epic, @Feature, @Story for better reporting
7. **Parallel Execution** - Faster test execution
8. **Clean Code** - Well-organized, commented, and maintainable code

## 📈 Reporting

The framework generates detailed Allure reports with:
- Test execution summary
- Step-by-step test execution details
- Request/Response logs
- Test categorization (Epic, Feature, Story)
- Severity levels
- Execution timeline
- Trend analysis

## 🔗 Resources

- [FakeStore API Documentation](https://fakestoreapi.com/docs)
- [RestAssured Documentation](https://rest-assured.io/)
- [Allure Documentation](https://docs.qameta.io/allure/)
