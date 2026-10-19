package kz.iitu.springlab.handler;
import kz.iitu.springlab.catalog.BookNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice(annotations = Controller.class)
public class PageExceptionHandler {
@ExceptionHandler(BookNotFoundException.class)
public String handleBookNotFound(BookNotFoundException ex, Model model) {
model.addAttribute("message", ex.getMessage());
return "error/not-found";
}
}
