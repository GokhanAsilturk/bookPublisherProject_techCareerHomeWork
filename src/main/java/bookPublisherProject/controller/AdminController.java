package bookPublisherProject.controller;

import bookPublisherProject.data.request.adminRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.adminRequests.DeleteAuthorRequest;
import bookPublisherProject.data.request.adminRequests.DeleteBookRequest;
import bookPublisherProject.data.request.bookRequests.CreateBookAndAuthorRequest;
import bookPublisherProject.data.request.bookRequests.CreateBookRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookNameAndReleaseYearRequest;
import bookPublisherProject.data.response.TCResponse;
import bookPublisherProject.exception.BookException;
import bookPublisherProject.exception.ErrorResponse;
import bookPublisherProject.service.authorServices.AuthorService;
import bookPublisherProject.service.bookServices.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final AuthorService authorService;

    private final BookService bookService;

    @Autowired
    public AdminController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }


    //    1-) API, şirketin veritabanına yeni bir kitap kaydı oluşturmasına izin vermeli ve bu kayıt, kitabın başlığı,
    //açıklaması, yayın tarihi ve yazar bilgilerini (ad, e-posta ve bio) içermelidir.
    @PostMapping("/create/bookAndAuthor")
    public ResponseEntity<TCResponse<?>> createBookAndAuthor(@RequestBody CreateBookAndAuthorRequest createBookAndAuthorRequest) {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.createBookAndAuthor(createBookAndAuthorRequest))
                    .build());
        }
        catch (BookException bookException){
            return ResponseEntity.ok(
                    TCResponse.<ErrorResponse>builder()
                            .isSuccess(false)
                            .response(new ErrorResponse(new ArrayList<>()))
                            .build());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/create/author")
    public ResponseEntity<TCResponse<?>> createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest) {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(this.authorService.createAuthor(createAuthorRequest))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/create/book")
    public ResponseEntity<TCResponse<?>> createBook(@RequestBody CreateBookRequest createBookRequest) {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.createBook(createBookRequest))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/author")
    public ResponseEntity<TCResponse<?>> deleteAuthor(@RequestBody DeleteAuthorRequest deleteAuthorRequest) {
        this.authorService.deleteAuthor(deleteAuthorRequest);
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/book")
    public ResponseEntity<TCResponse<?>> deleteBook(@RequestBody DeleteBookRequest deleteBookRequest) {
        this.bookService.deleteBook(deleteBookRequest);
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("{bookId}/authorName/change/{authorName}")
    public ResponseEntity<TCResponse<?>> updateNameOfAuthorByBook(
            @PathVariable("bookId") String bookId, @PathVariable("authorName") String authorName) {
        try {
            return ResponseEntity.ok(TCResponse.builder()
                    .isSuccess(true)
                    .response(bookService.updateNameOfAuthorByBook(bookId, authorName))
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


}
