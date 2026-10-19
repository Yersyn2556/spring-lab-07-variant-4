
package kz.iitu.springlab.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookOptionsController {

    @GetMapping("/books/options")
    public String showForm(Model model) {
        model.addAttribute("form", new BookOptionsForm());
        return "book-options";
    }

    @PostMapping("/books/options")
    public String submitForm(
            @Valid @ModelAttribute("form") BookOptionsForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "book-options";
        }

        model.addAttribute("selectedOptions", form.getOptions());
        return "book-options-result";
    }
}
