Lab 7 extends Lab 6. Individual assignment #4 (nested GET /api/books/{id}/reviews) IS INCLUDED.
Read LAB7_VARIANT4_README.md for the file list, Swagger checks, screenshots, and defense text.

JDK 25, Maven, Spring Boot 4.1.1. Stop the old server before running this project.
Port configured to 8081 (matching the user's previously working setup).
Swagger UI: http://localhost:8081/swagger-ui.html
Example reviews: GET /api/books/1/reviews -> 200 (list)
No reviews: GET /api/books/3/reviews -> 200 []
Missing book: GET /api/books/999/reviews -> 404 ProblemDetail

Main CRUD checks: 200, 201 + Location, 204, 400, 404, 409.
The updated archive must still be built/tested in IntelliJ with JDK 25.
