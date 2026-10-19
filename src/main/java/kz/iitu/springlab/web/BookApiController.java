package kz.iitu.springlab.web;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.iitu.springlab.catalog.Book;
import kz.iitu.springlab.catalog.BookService;
import kz.iitu.springlab.web.dto.*;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
@Tag(name="Books",description="The catalogue of books")
@RestController
@RequestMapping("/api/books")
public class BookApiController {
    private final BookService service;
    private final BookMapper mapper;
    public BookApiController(BookService service, BookMapper mapper) {this.service=service;this.mapper=mapper;}
    @Operation(summary="List books, optionally filtered by author")
    @GetMapping
    public List<BookResponse> list(@RequestParam(required=false) String author) {
        return mapper.toResponseList(service.findAll(author));
    }
    @Operation(summary="Find a book by its identifier")
    @ApiResponses({
        @ApiResponse(responseCode="200",description="The book was found"),
        @ApiResponse(responseCode="404",description="No book with this id",content=@Content(schema=@Schema(implementation=ProblemDetail.class)))
    })
    @GetMapping("/{id}")
    public BookResponse find(@Parameter(description="Identifier of the book",example="42") @PathVariable long id) {
        return mapper.toResponse(service.findById(id));
    }
    @Operation(summary="Create a book")
    @ApiResponses({@ApiResponse(responseCode="201",description="Created with Location header"),@ApiResponse(responseCode="400",description="Validation error"),@ApiResponse(responseCode="409",description="Duplicate title and author")})
    @PostMapping
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookRequest request) {
        Book saved=service.create(mapper.toDomain(request));
        return ResponseEntity.created(URI.create("/api/books/"+saved.id())).body(mapper.toResponse(saved));
    }
    @Operation(summary="Replace a book")
    @PutMapping("/{id}")
    public BookResponse update(@PathVariable long id,@Valid @RequestBody BookRequest request) {
        return mapper.toResponse(service.update(id,mapper.toDomain(request)));
    }
    @Operation(summary="Delete a book")
    @ApiResponses({@ApiResponse(responseCode="204",description="Deleted"),@ApiResponse(responseCode="404",description="Book not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);return ResponseEntity.noContent().build();
    }
}
