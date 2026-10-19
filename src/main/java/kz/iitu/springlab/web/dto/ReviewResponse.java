package kz.iitu.springlab.web.dto;

/** Public API shape of a book review (separate from BookResponse). */
public record ReviewResponse(Long id, String reviewer, int rating, String comment) {
}
