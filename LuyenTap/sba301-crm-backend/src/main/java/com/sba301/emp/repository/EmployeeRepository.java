src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── projectname
│   │               ├── config
│   │               │   └── WebConfig.java          // Configuration classes (e.g., CORS, security)
│   │               ├── controller
│   │               │   ├── UserController.java     // REST controllers for user-related endpoints
│   │               │   ├── ProductController.java  // REST controllers for product-related endpoints
│   │               │   └── OrderController.java    // REST controllers for order-related endpoints
│   │               ├── dto
│   │               │   ├── UserDTO.java            // Data Transfer Objects for user
│   │               │   ├── ProductDTO.java         // Data Transfer Objects for product
│   │               │   └── OrderDTO.java           // Data Transfer Objects for order
│   │               ├── exception
│   │               │   ├── GlobalExceptionHandler.java // Global exception handling
│   │               │   └── ResourceNotFoundException.java // Custom exception for not found resources
│   │               ├── model
│   │               │   ├── User.java                // User entity
│   │               │   ├── Product.java             // Product entity
│   │               │   └── Order.java               // Order entity
│   │               ├── repository
│   │               │   ├── UserRepository.java      // Repository interface for User
│   │               │   ├── ProductRepository.java   // Repository interface for Product
│   │               │   └── OrderRepository.java     // Repository interface for Order
│   │               ├── service
│   │               │   ├── UserService.java         // Service layer for User
│   │               │   ├── ProductService.java      // Service layer for Product
│   │               │   └── OrderService.java        // Service layer for Order
│   │               └── Application.java              // Main application class
│   └── resources
│       ├── application.properties                    // Application configuration properties
│       ├── static                                    // Static resources (if any)
│       └── templates                                 // Template files (if using Thymeleaf or similar)
└── test
    └── java
        └── com
            └── example
                └── projectname
                    ├── controller
                    │   ├── UserControllerTest.java  // Unit tests for UserController
                    │   ├── ProductControllerTest.java // Unit tests for ProductController
                    │   └── OrderControllerTest.java // Unit tests for OrderController
                    ├── service
                    │   ├── UserServiceTest.java     // Unit tests for UserService
                    │   ├── ProductServiceTest.java  // Unit tests for ProductService
                    │   └── OrderServiceTest.java    // Unit tests for OrderService
                    └── ApplicationTests.java         // General application tests