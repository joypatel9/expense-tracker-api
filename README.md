# expense-tracker-api

REST API for tracking expenses, like [this](https://www.youtube.com/watch?v=5VUjP1wMqoE), but using Spring Data JPA,
Lombok etc.

Created using Spring Boot and Spring Data JPA. JSON Web Token (JWT) used to add authentication.

## Setup and Installation

1. Clone repo from GitHub
   ```
   git clone https://github.com/joypatel9/expense-tracker-api.git
   cd expense-tracker-api
   ```
1. Create a postgres database
1. Update the database name, username and password in `src/main/resources/application.properties`
1. Run the spring boot application using `./mvnw spring-boot:run`
1. This runs at port 8080 and hence all endpoints can be accessed starting from http://localhost:8080. For testing, you
   could use Postman collection ExpenseTracker, using the environment Expense tracker, which are available in
   src/test/resources/postman

### Available Endpoints

1. `POST /api/users` - registers a user
1. `POST /api/users/login` - logs in a user, and returns a bearer JWT token
1. `GET /api/categories` - gets all the categories of the current user
1. `GET /api/categories/{categoryId}` - gets the category of the given categoryId
1. `POST /api/categories` - adds a new category for the current user
1. `PUT /api/categories/{categoryId} ` - updates the category of the given categoryId
1. `GET /api/categories/{categoryId}/transactions` - gets all transactions of the given categoryId
1. `POST /api/categories/{categoryId}/transactions` - adds a transaction to the category of given categoryId
1. `GET /api/categories/{categoryId}/transactions/{transactionId}` - gets the transaction of given categoryId and
   transactionId
1. `PUT /api/categories/{categoryId}/transactions/{transactionId}` - updates the transaction of given categoryId and
   transactionId
1. `DEL /api/categories/{categoryId}/transactions/{transactionId}` - deletes the transaction of given categoryId and
   transactionId
1. `DEL /api/categories/{categoryId}` - deletes the given category of given categoryId and all its transactions
