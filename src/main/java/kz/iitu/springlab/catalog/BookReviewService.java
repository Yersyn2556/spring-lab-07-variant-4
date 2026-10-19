package kz.iitu.springlab.catalog;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReviewService {
    private final BookService bookService;
    private final BookReviewRepository reviewRepository;

    public BookReviewService(BookService bookService, BookReviewRepository reviewRepository) {
        this.bookService = bookService;
        this.reviewRepository = reviewRepository;
    }

    public List<BookReview> findReviewsForBook(Long bookId) {
        // A book with no reviews returns [] (200), but an absent book must return 404.
        bookService.findById(bookId);
        return reviewRepository.findByBookId(bookId);
    }
}
