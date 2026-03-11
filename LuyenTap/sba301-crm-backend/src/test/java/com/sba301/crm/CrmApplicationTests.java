src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           ├── config
│   │           │   └── WebConfig.java          // Configuration classes (e.g., CORS, Security)
│   │           ├── controller
│   │           │   ├── UserController.java      // REST controllers for user-related endpoints
│   │           │   ├── ProductController.java   // REST controllers for product-related endpoints
│   │           │   └── OrderController.java     // REST controllers for order-related endpoints
│   │           ├── dto
│   │           │   ├── UserDTO.java             // Data Transfer Objects for User
│   │           │   ├── ProductDTO.java          // Data Transfer Objects for Product
│   │           │   └── OrderDTO.java            // Data Transfer Objects for Order
│   │           ├── exception
│   │           │   ├── CustomException.java      // Custom exception handling
│   │           │   └── GlobalExceptionHandler.java // Global exception handler
│   │           ├── model
│   │           │   ├── User.java                 // User entity
│   │           │   ├── Product.java              // Product entity
│   │           │   └── Order.java                // Order entity
│   │           ├── repository
│   │           │   ├── UserRepository.java       // Repository interface for User
│   │           │   ├── ProductRepository.java    // Repository interface for Product
│   │           │   └── OrderRepository.java      // Repository interface for Order
│   │           ├── service
│   │           │   ├── UserService.java          // Service layer for User
│   │           │   ├── ProductService.java       // Service layer for Product
│   │           │   └── OrderService.java         // Service layer for Order
│   │           └── Application.java               // Main application class
│   └── resources
│       ├── application.properties                 // Application configuration properties
│       ├── static                                 // Static resources (if any)
│       └── templates                              // Thymeleaf templates (if using)
└── test
    └── java
        └── com
            └── example
                ├── controller
                │   ├── UserControllerTest.java   // Unit tests for UserController
                │   ├── ProductControllerTest.java // Unit tests for ProductController
                │   └── OrderControllerTest.java   // Unit tests for OrderController
                ├── service
                │   ├── UserServiceTest.java       // Unit tests for UserService
                │   ├── ProductServiceTest.java    // Unit tests for ProductService
                │   └── OrderServiceTest.java      // Unit tests for OrderService
                └── ApplicationTest.java            // Main application test class