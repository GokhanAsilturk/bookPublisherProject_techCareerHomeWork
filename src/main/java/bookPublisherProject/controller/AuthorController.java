package bookPublisherProject.controller;

import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.authorRequests.RegisterAuthorRequest;
import bookPublisherProject.data.request.authorRequests.SoftDeleteAuthorRequest;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;
import bookPublisherProject.data.request.bookRequests.SoftDeleteBookRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookAndAuthorRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookNameAndReleaseYearRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookRequest;
import bookPublisherProject.data.response.TCResponse;
import bookPublisherProject.exception.BookRuntimeException;
import bookPublisherProject.service.authorServices.AuthorService;
import bookPublisherProject.service.bookServices.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ExceptionHandler({BookRuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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

    @DeleteMapping("/delete/author")
    public ResponseEntity<TCResponse<?>> deleteAuthor(@RequestBody SoftDeleteAuthorRequest softDeleteAuthorRequest) {
        this.authorService.deleteAuthor(softDeleteAuthorRequest.convertToDeleteRequest());
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //3-) API, yazarın bilgilerini de içeren bir kitap kaydının bilgilerini güncellemek için
    // "Mürekkep Kalem Kitaplarına" izin vermelidir.
    @PatchMapping("/update/bookAndAuthor")
    public ResponseEntity<TCResponse<?>> updateBookAndAuthor(
            @RequestBody UpdateBookAndAuthorRequest request) {

        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.updateBookAndAuthor(request))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/update/book")
    public ResponseEntity<TCResponse<?>> updateBook(
            @RequestBody UpdateBookRequest updateBookRequest) {

        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.updateBook(updateBookRequest))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/update/author")
    public ResponseEntity<TCResponse<?>> updateAuthor(
            @RequestBody UpdateAuthorRequest updateAuthorRequest) {

        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(authorService.updateAuthor(updateAuthorRequest))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/update/bookNameAndReleaseYear")
    public ResponseEntity<TCResponse<?>> updateBookNameAndReleaseYear(
            @RequestBody UpdateBookNameAndReleaseYearRequest request) {

        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.updateBookNameAndReleaseYear(request))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //    2-) API, bir kitap kaydını ID'sine göre almak ve veritabanındaki tüm kitapların bir listesini almak için
    //    "Mürekkep Kalem Kitaplarına" izin vermelidir.
    @GetMapping("{id}/get/book/by/id")
    public ResponseEntity<TCResponse<?>> getBookById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.getBookById(id))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //    2-) API, bir kitap kaydını ID'sine göre almak ve veritabanındaki tüm kitapların bir listesini almak için
    //    "Mürekkep Kalem Kitaplarına" izin vermelidir.
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


}
