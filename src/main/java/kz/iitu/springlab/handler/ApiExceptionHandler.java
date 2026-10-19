package kz.iitu.springlab.handler;
import kz.iitu.springlab.catalog.BookNotFoundException;
import kz.iitu.springlab.catalog.DuplicateTitleException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.net.URI;
import java.util.List;
@RestControllerAdvice(annotations=RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ProblemDetail handleNotFound(BookNotFoundException ex) {
        ProblemDetail p=ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
        p.setTitle("Book not found");p.setType(URI.create("https://api.iitu.kz/errors/book-not-found"));return p;
    }
    @ExceptionHandler(DuplicateTitleException.class)
    public ProblemDetail handleDuplicate(DuplicateTitleException ex) {
        ProblemDetail p=ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,ex.getMessage());
        p.setTitle("Duplicate title");
        p.setType(URI.create("https://api.iitu.kz/errors/duplicate-title"));
        p.setProperty("conflictingTitle",ex.getTitle());
        return p;
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatusCode status,WebRequest request) {
        ProblemDetail p=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Request validation failed");
        p.setTitle("Validation failed");
        p.setType(URI.create("https://api.iitu.kz/errors/validation-failed"));
        p.setProperty("fields",ex.getBindingResult().getFieldErrors().stream().map(e -> e.getField()).distinct().toList());
        return handleExceptionInternal(ex,p,headers,status,request);
    }
}
