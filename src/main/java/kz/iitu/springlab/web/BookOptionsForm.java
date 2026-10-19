package kz.iitu.springlab.web;

import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class BookOptionsForm {

    @NotEmpty(message = "Выберите хотя бы один вариант.")
    private List<String> options = new ArrayList<>();

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}