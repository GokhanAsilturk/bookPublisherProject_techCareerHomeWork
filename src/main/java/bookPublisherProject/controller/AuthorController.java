package bookPublisherProject.controller;

import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.response.TCResponse;
import bookPublisherProject.service.authorServices.AuthorService;
import bookPublisherProject.service.bookServices.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;
    private BookService bookService;

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





}
