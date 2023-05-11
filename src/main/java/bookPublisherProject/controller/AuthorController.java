package bookPublisherProject.controller;

import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.authorRequests.RegisterAuthorRequest;
import bookPublisherProject.data.request.bookRequests.SoftDeleteBookRequest;
import bookPublisherProject.data.response.TCResponse;
import bookPublisherProject.service.authorServices.AuthorService;
import bookPublisherProject.service.bookServices.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final BookService bookService;

    private final AuthorService authorService;

    @Autowired
    public AuthorController(BookService bookService, AuthorService authorService) {

        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostMapping("/register")
    public ResponseEntity<TCResponse<?>> register(@RequestBody RegisterAuthorRequest registerAuthorRequest) {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(this.authorService.registerAuthor(registerAuthorRequest))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/publish/new/book")
    public ResponseEntity<TCResponse<?>> publishNewBook(@RequestBody PublishNewBookRequest publishNewBookRequest) {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(this.bookService.publishNewBook(publishNewBookRequest))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/book")
    public ResponseEntity<TCResponse<?>> deleteBook(@RequestBody SoftDeleteBookRequest softDeleteBookRequest) {
        this.bookService.deleteBook(softDeleteBookRequest.convertToDeleteRequest());
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
