package kz.iitu.springlab.catalog;
import java.time.Instant;
public record Book(Long id, String title, String author, int year, String genre, boolean available, Instant createdAt) {
    public Book(Long id, String title, String author, int year, String genre, boolean available) {
        this(id, title, author, year, genre, available, Instant.now());
    }
}
