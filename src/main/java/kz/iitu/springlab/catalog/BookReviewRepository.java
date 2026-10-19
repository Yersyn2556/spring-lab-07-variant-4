package kz.iitu.springlab.catalog;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookReviewRepository {
    // Example reviews for seeded books; the existing book repository is also in memory.
    private final List<BookReview> reviews = List.of(
            new BookReview(1L, 1L, "Reader A", 5, "Practical advice with clear examples."),
            new BookReview(2L, 1L, "Reader B", 4, "Helpful explanations about writing clean code."),
            new BookReview(3L, 2L, "Reader C", 5, "Useful tips for Java developers.")
    );

    public List<BookReview> findByBookId(Long bookId) {
        return reviews.stream()
                .filter(review -> review.bookId().equals(bookId))
                .toList();
    }
}
