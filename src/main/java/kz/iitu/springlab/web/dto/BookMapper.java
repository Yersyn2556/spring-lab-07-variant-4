package kz.iitu.springlab.web.dto;
import kz.iitu.springlab.catalog.Book;
import kz.iitu.springlab.web.Genre;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class BookMapper {
    public Book toDomain(BookRequest request) {
        return new Book(null,request.title(),request.author(),request.year(),request.genre().name(),request.available());
    }
    public BookResponse toResponse(Book book) {
        return new BookResponse(book.id(),book.title(),book.author(),book.year(),Genre.valueOf(book.genre()),book.available(),book.createdAt());
    }
    public List<BookResponse> toResponseList(List<Book> books) { return books.stream().map(this::toResponse).toList(); }
}
