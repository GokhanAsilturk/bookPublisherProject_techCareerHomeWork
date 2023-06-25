package bookPublisherProject.controller;

import bookPublisherProject.data.request.userRequests.LoginRequest;
import bookPublisherProject.data.types.response.TCResponse;
import bookPublisherProject.exception.CustomExceptionHandler;
import bookPublisherProject.exception.DataNotFoundException;
import bookPublisherProject.exception.ErrorResponse;
import bookPublisherProject.service.authorServices.AuthorService;
import bookPublisherProject.service.bookServices.BookService;
import bookPublisherProject.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final BookService bookService;
    private final AuthorService authorService;

    private final UserService userService;

    @Autowired
    public UserController(BookService bookService, AuthorService authorService, UserService userService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<TCResponse<?>> login(@RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(userService.login(loginRequest))
                .build());

    }

    @GetMapping("{id}/get/book/by/id")
    public ResponseEntity<TCResponse<?>> getById(@RequestParam("id") String id) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(bookService.getBookById(id))
                .build());
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<TCResponse<?>> getAllBooks() {

            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.getAllBooks())
                    .build());

    }

    @GetMapping("/getAllAuthors")
    public ResponseEntity<TCResponse<?>> getAllAuthors() {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(authorService.getAllAuthors())
                .build());
    }

    @GetMapping("{authorName}/getBookEntities/by/authorName")
    public ResponseEntity<TCResponse<?>> getBooksByAuthorName(@PathVariable("authorName") String authorName) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(bookService.getBooksByAuthorName(authorName))
                .build());

    }
}
