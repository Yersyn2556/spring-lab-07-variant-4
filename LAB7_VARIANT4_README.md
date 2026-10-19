# Laboratory Work 7 — Variant 4: Nested Resource

This is **Lab 6 continued**, with the five book REST operations from Lab 7 preserved and the individual assignment #4 implemented.

## Variant 4 requirement

`GET /api/books/{id}/reviews` returns a JSON array of **ReviewResponse DTOs** and HTTP 200 if the book exists. A missing parent book returns HTTP 404 with `application/problem+json` from the existing `ApiExceptionHandler`.

## Where the new files are

- `src/main/java/kz/iitu/springlab/catalog/BookReview.java` — domain model with the parent `bookId`
- `.../catalog/BookReviewRepository.java` — in-memory example review data
- `.../catalog/BookReviewService.java` — checks book existence before looking up reviews
- `.../web/dto/ReviewResponse.java` — response DTO (different from BookResponse)
- `.../web/dto/ReviewMapper.java` — maps domain reviews to response DTOs
- `.../web/BookReviewController.java` — nested GET endpoint and Swagger documentation
- `src/test/java/kz/iitu/springlab/BookReviewServiceTests.java` — three JUnit tests

Existing book REST endpoints, HTML forms and other Lab 6 functionality remain unchanged. `ApiExceptionHandler` also has a small fix: duplicate book errors keep `title: "Duplicate title"` and use `conflictingTitle` for the original book title.

## Run on Windows (IntelliJ IDEA)

1. Stop the previous application via the red Stop square (otherwise port 8081 is occupied).
2. Open the `spring-lab-01` folder (the folder containing `pom.xml`) in IntelliJ.
3. Wait for Maven to reload and run `SpringLab01Application` with **JDK 25**.
4. Open `http://localhost:8081/swagger-ui.html` and find **Book Reviews**.
5. Expand **GET /api/books/{id}/reviews** > Try it out. Test these values:

| id | Expected status | Expected response |
|---|---|---|
| 1 | 200 | JSON array with two reviews |
| 3 | 200 | Empty JSON array (`[]`) |
| 999 | 404 | `application/problem+json`, book not found |

The samples use fictional in-memory reviews. They restart with the application, just like the seeded book catalog.

## For the report

Take screenshots of `GET /api/books/1/reviews` (200 with review list), `GET /api/books/999/reviews` (404 with ProblemDetail), and Swagger UI showing the nested resource. Add an example listing of `ReviewResponse`, `BookReviewController`, `BookReviewService` and the observed results.

**Explanation for defense (Kazakh):**

> Біздің жеке нұсқамыз — 4, Nested Resource. Біз кітапқа байланысты пікірлерді көрсететін GET /api/books/{id}/reviews эндпоинтын жасадық. BookReviewService алдымен кітаптың бар-жоғын тексереді. Егер кітап бар болса, пікірлер ReviewResponse DTO арқылы JSON тізімі ретінде қайтарылады. Кітап табылмаса, ортақ ApiExceptionHandler 404 және ProblemDetail қайтарады. Кітап бар, бірақ пікірлер жоқ болса, 200 және бос тізім қайтарылады.
