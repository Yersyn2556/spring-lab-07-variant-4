package kz.iitu.springlab.web.dto;

import kz.iitu.springlab.catalog.BookReview;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {
    public ReviewResponse toResponse(BookReview review) {
        return new ReviewResponse(review.id(), review.reviewer(), review.rating(), review.comment());
    }

    public List<ReviewResponse> toResponseList(List<BookReview> reviews) {
        return reviews.stream().map(this::toResponse).toList();
    }
}
