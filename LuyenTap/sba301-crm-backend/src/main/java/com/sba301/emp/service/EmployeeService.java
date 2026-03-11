src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── projectname
│   │               ├── config
│   │               │   ├── SecurityConfig.java
│   │               │   └── WebConfig.java
│   │               ├── controller
│   │               │   ├── UserController.java
│   │               │   ├── ProductController.java
│   │               │   └── OrderController.java
│   │               ├── dto
│   │               │   ├── UserDTO.java
│   │               │   ├── ProductDTO.java
│   │               │   └── OrderDTO.java
│   │               ├── exception
│   │               │   ├── ResourceNotFoundException.java
│   │               │   └── GlobalExceptionHandler.java
│   │               ├── model
│   │               │   ├── User.java
│   │               │   ├── Product.java
│   │               │   └── Order.java
│   │               ├── repository
│   │               │   ├── UserRepository.java
│   │               │   ├── ProductRepository.java
│   │               │   └── OrderRepository.java
│   │               ├── service
│   │               │   ├── UserService.java
│   │               │   ├── ProductService.java
│   │               │   └── OrderService.java
│   │               └── Application.java
│   └── resources
│       ├── application.properties
│       ├── static
│       └── templates
└── test
    └── java
        └── com
            └── example
                └── projectname
                    ├── controller
                    │   ├── UserControllerTest.java
                    │   ├── ProductControllerTest.java
                    │   └── OrderControllerTest.java
                    ├── service
                    │   ├── UserServiceTest.java
                    │   ├── ProductServiceTest.java
                    │   └── OrderServiceTest.java
                    └── ApplicationTest.java