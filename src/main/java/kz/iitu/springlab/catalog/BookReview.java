package kz.iitu.springlab.catalog;

/** A review stored in the demonstration repository. */
public record BookReview(Long id, Long bookId, String reviewer, int rating, String comment) {
}
