package bookPublisherProject.controller;

import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.authorRequests.RegisterAuthorRequest;
import bookPublisherProject.data.request.authorRequests.SoftDeleteAuthorRequest;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;
import bookPublisherProject.data.request.bookRequests.SoftDeleteBookRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookAndAuthorRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookNameAndReleaseYearRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookRequest;
import bookPublisherProject.data.types.response.TCResponse;
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

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(this.authorService.registerAuthor(registerAuthorRequest))
                .build());

    }

    @PostMapping("/publish/new/book")
    public ResponseEntity<TCResponse<?>> publishNewBook(@RequestBody PublishNewBookRequest publishNewBookRequest) {
        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(this.bookService.publishNewBook(publishNewBookRequest))
                .build());
    }

    @DeleteMapping("/delete/book")
    public ResponseEntity<TCResponse<?>> deleteBook(@RequestBody SoftDeleteBookRequest softDeleteBookRequest) {
        this.bookService.deleteBook(softDeleteBookRequest.convertToDeleteRequest());

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .build());

    }


    @DeleteMapping("/delete/author")
    public ResponseEntity<TCResponse<?>> deleteAuthor(@RequestBody SoftDeleteAuthorRequest softDeleteAuthorRequest) {

        this.authorService.deleteAuthor(softDeleteAuthorRequest.convertToDeleteRequest());
        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .build());
    }

    //3-) API, yazarın bilgilerini de içeren bir kitap kaydının bilgilerini güncellemek için
    // "Mürekkep Kalem Kitaplarına" izin vermelidir.
    @PatchMapping("/update/bookAndAuthor")
    public ResponseEntity<TCResponse<?>> updateBookAndAuthor(
            @RequestBody UpdateBookAndAuthorRequest request) {
        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(bookService.updateBookAndAuthor(request))
                .build());

    }

    @PatchMapping("/update/book")
    public ResponseEntity<TCResponse<?>> updateBook(
            @RequestBody UpdateBookRequest updateBookRequest) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(bookService.updateBook(updateBookRequest))
                .build());

    }

    @PatchMapping("/update/author")
    public ResponseEntity<TCResponse<?>> updateAuthor(
            @RequestBody UpdateAuthorRequest updateAuthorRequest) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(authorService.updateAuthor(updateAuthorRequest))
                .build());

    }

    @PatchMapping("/update/bookNameAndReleaseYear")
    public ResponseEntity<TCResponse<?>> updateBookNameAndReleaseYear(
            @RequestBody UpdateBookNameAndReleaseYearRequest request) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(bookService.updateBookNameAndReleaseYear(request))
                .build());

    }

    //TODO burası sıkıntılı. 404 döndürüyor.

    //    2-) API, bir kitap kaydını ID'sine göre almak ve veritabanındaki tüm kitapların bir listesini almak için
    //    "Mürekkep Kalem Kitaplarına" izin vermelidir.
    @GetMapping("{bookId}/get/book/by/id")
    public ResponseEntity<TCResponse<?>> getBookById(@RequestParam("bookId") String id) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(bookService.getBookById(id))
                .build());

    }

    //    2-) API, bir kitap kaydını ID'sine göre almak ve veritabanındaki tüm kitapların bir listesini almak için
    //    "Mürekkep Kalem Kitaplarına" izin vermelidir.
    @GetMapping("/getAllBooks")
    public ResponseEntity<TCResponse<?>> getAllBooks() {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(bookService.getAllBooks())
                .build());

    }


}
