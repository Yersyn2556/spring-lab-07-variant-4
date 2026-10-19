package kz.iitu.springlab;

import kz.iitu.springlab.catalog.BookNotFoundException;
import kz.iitu.springlab.catalog.BookRepository;
import kz.iitu.springlab.catalog.BookReviewRepository;
import kz.iitu.springlab.catalog.BookReviewService;
import kz.iitu.springlab.catalog.BookService;
import kz.iitu.springlab.web.dto.ReviewMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookReviewServiceTests {
    private final BookReviewService service = new BookReviewService(
            new BookService(new BookRepository()), new BookReviewRepository());

    @Test
    void existingBookHasReviewResponseDtos() {
        var reviews = new ReviewMapper().toResponseList(service.findReviewsForBook(1L));
        assertEquals(2, reviews.size());
        assertEquals("Reader A", reviews.getFirst().reviewer());
        assertEquals(5, reviews.getFirst().rating());
    }

    @Test
    void existingBookWithNoReviewsHasEmptyCollection() {
        assertTrue(service.findReviewsForBook(3L).isEmpty());
    }

    @Test
    void missingBookThrowsNotFoundException() {
        assertThrows(BookNotFoundException.class, () -> service.findReviewsForBook(999L));
    }
}
