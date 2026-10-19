package kz.iitu.springlab.web;

import jakarta.validation.Valid;
import kz.iitu.springlab.catalog.Book;
import kz.iitu.springlab.catalog.BookService;
import kz.iitu.springlab.exception.BookNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookPageController {

    private final BookService bookService;

    public BookPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/books/new")
    public String newBook(Model model) {
        model.addAttribute("form", new BookForm());
        model.addAttribute("genres", Genre.values());
        return "books/form";
    }

    @PostMapping("/books")
    public String addBook(
            @Valid @ModelAttribute("form") BookForm form,
            BindingResult binding,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (binding.hasErrors()) {
            model.addAttribute("genres", Genre.values());
            return "books/form";
        }

        Book book = new Book(
                null,
                form.getTitle(),
                form.getAuthor(),
                form.getYear(),
                form.getGenre().name(),
                form.isAvailable()
        );

        bookService.create(book);

        redirectAttributes.addFlashAttribute(
                "message", "Book added successfully!"
        );

        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);

        if (book == null) {
            throw new BookNotFoundException(id);
        }

        model.addAttribute("book", book);
        return "books/detail";
    }
}