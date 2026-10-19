package kz.iitu.springlab.web.dto;
import jakarta.validation.constraints.*;
import kz.iitu.springlab.web.Genre;
import kz.iitu.springlab.validation.NoDigits;
public record BookRequest(
    @NotBlank @Size(max=120) String title,
    @NotBlank @NoDigits String author,
    @Min(1450) @Max(2100) int year,
    @NotNull Genre genre,
    boolean available
) {}
