package kz.iitu.springlab.catalog;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class BookRepository {
    private final List<Book> books = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(4);
    public BookRepository() {
        books.add(new Book(1L, "Clean Code", "Robert Martin", 2008, "PROGRAMMING", true));
        books.add(new Book(2L, "Effective Java", "Joshua Bloch", 2018, "PROGRAMMING", true));
        books.add(new Book(3L, "The Hobbit", "J.R.R. Tolkien", 1937, "FANTASY", false));
    }
    public synchronized List<Book> findAll() { return new ArrayList<>(books); }
    public synchronized Optional<Book> findById(Long id) { return books.stream().filter(b -> b.id().equals(id)).findFirst(); }
    public synchronized Book save(Book book) {
        Book saved = new Book(nextId.getAndIncrement(), book.title(), book.author(), book.year(), book.genre(), book.available());
        books.add(saved); return saved;
    }
    public synchronized Optional<Book> update(Long id, Book changed) {
        for (int i=0; i<books.size(); i++) {
            Book old=books.get(i);
            if(old.id().equals(id)) {
                Book updated=new Book(id,changed.title(),changed.author(),changed.year(),changed.genre(),changed.available(),old.createdAt());
                books.set(i,updated);return Optional.of(updated);
            }
        }
        return Optional.empty();
    }
    public synchronized boolean deleteById(Long id) { return books.removeIf(b -> b.id().equals(id)); }
    public synchronized boolean existsByTitleAndAuthor(String title, String author, Long excludingId) {
        return books.stream().anyMatch(b -> !b.id().equals(excludingId) && b.title().equalsIgnoreCase(title.trim()) && b.author().equalsIgnoreCase(author.trim()));
    }
}
