package bookPublisherProject.controller;

import bookPublisherProject.data.response.TCResponse;
import bookPublisherProject.service.authorServices.AuthorService;
import bookPublisherProject.service.bookServices.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public UserController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @GetMapping("/getAllBooks")
    public ResponseEntity<TCResponse<?>> getAllBooks() {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.getAllBooks())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getAllAuthors")
    public ResponseEntity<TCResponse<?>> getAllAuthors() {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(authorService.getAllAuthors())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
