package kz.iitu.springlab.catalog;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookService {
    private final BookRepository repository;
    public BookService(BookRepository repository) { this.repository=repository; }
    public List<Book> findAll() { return repository.findAll(); }
    public List<Book> findAll(String author) {
        if (author==null || author.isBlank()) return findAll();
        return findAll().stream().filter(b -> b.author().toLowerCase().contains(author.toLowerCase())).toList();
    }
    public Book findById(Long id) { return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id)); }
    public synchronized Book create(Book book) {
        if(repository.existsByTitleAndAuthor(book.title(),book.author(),null)) throw new DuplicateTitleException(book.title());
        return repository.save(book);
    }
    public synchronized Book update(Long id, Book book) {
        findById(id);
        if(repository.existsByTitleAndAuthor(book.title(),book.author(),id)) throw new DuplicateTitleException(book.title());
        return repository.update(id,book).orElseThrow(() -> new BookNotFoundException(id));
    }
    public void delete(Long id) { if(!repository.deleteById(id)) throw new BookNotFoundException(id); }
}
