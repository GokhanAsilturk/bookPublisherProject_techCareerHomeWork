package bookPublisherProject.controller;

import bookPublisherProject.data.request.userRequests.LoginRequest;
import bookPublisherProject.data.response.TCResponse;
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
public ResponseEntity<TCResponse<?>> login(@RequestBody LoginRequest loginRequest){
    try {
        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(userService.login(loginRequest))
                .build());
    } catch (Exception e) {
        return ResponseEntity.internalServerError().build();
    }
}
    @GetMapping("{id}/get/book/by/id")
    public ResponseEntity<TCResponse<?>> getById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.getBookById(id))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
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

    @GetMapping("{authorID}/getBooks/by/authorName")
    public ResponseEntity<TCResponse<?>> getBooksByAuthorName(@PathVariable("authorName") String authorName) {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.getBooksByAuthorName(authorName))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
