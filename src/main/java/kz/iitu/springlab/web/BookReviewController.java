package kz.iitu.springlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.iitu.springlab.catalog.BookReviewService;
import kz.iitu.springlab.web.dto.ReviewMapper;
import kz.iitu.springlab.web.dto.ReviewResponse;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Book Reviews", description = "Reviews nested under their parent book")
@RestController
@RequestMapping("/api/books")
public class BookReviewController {
    private final BookReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public BookReviewController(BookReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @Operation(summary = "Get reviews for a book",
            description = "Returns a list of reviews. An existing book with no reviews returns an empty list.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reviews for the book, possibly an empty array"),
            @ApiResponse(responseCode = "404", description = "The book does not exist",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/{id}/reviews")
    public List<ReviewResponse> findReviews(
            @Parameter(description = "Identifier of the parent book", example = "1")
            @PathVariable Long id) {
        return reviewMapper.toResponseList(reviewService.findReviewsForBook(id));
    }
}
