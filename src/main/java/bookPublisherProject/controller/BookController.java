package bookPublisherProject.controller;

import bookPublisherProject.data.request.bookRequests.*;
import bookPublisherProject.data.response.TCResponse;
import bookPublisherProject.service.bookServices.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
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

//    //    1-) API, şirketin veritabanına yeni bir kitap kaydı oluşturmasına izin vermeli ve bu kayıt, kitabın başlığı,
////    açıklaması, yayın tarihi ve yazar bilgilerini (ad, e-posta ve bio) içermelidir.
//    @PostMapping("/create/bookAndAuthor")
//    public ResponseEntity<TCResponse<?>> createBookAndAuthor(@RequestBody CreateBookAndAuthorRequest createBookAndAuthorRequest) {
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.createBookAndAuthor(createBookAndAuthorRequest))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }


//    //    2-) API, bir kitap kaydını ID'sine göre almak ve veritabanındaki tüm kitapların bir listesini almak için
//    //    "Mürekkep Kalem Kitaplarına" izin vermelidir.
//    @GetMapping("{id}/getById")
//    public ResponseEntity<TCResponse<?>> getById(@PathVariable("id") String id) {
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.getBookById(id))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

//    //    2-) API, bir kitap kaydını ID'sine göre almak ve veritabanındaki tüm kitapların bir listesini almak için
//    //    "Mürekkep Kalem Kitaplarına" izin vermelidir.
//    @GetMapping("/getAll")
//    public ResponseEntity<TCResponse<?>> getAllBooks() {
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.getAllBooks())
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

//    //3-) API, yazarın bilgilerini de içeren bir kitap kaydının bilgilerini güncellemek için
//    // "Mürekkep Kalem Kitaplarına" izin vermelidir.
//    @PatchMapping("/update/bookAndAuthor")
//    public ResponseEntity<TCResponse<?>> updateBookAndAuthor(
//            @RequestBody UpdateBookAndAuthorRequest request) {
//
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.updateBookAndAuthor(request))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

//    //4-) API, bir kitap kaydını veritabanından silmek için "Mürekkep Kalem Kitaplarına" izin vermeli.
//    @DeleteMapping("/delete/book")
//    public ResponseEntity<TCResponse<?>> deleteBook(@RequestBody SoftDeleteBookRequest softDeleteBookRequest) {
//        this.bookService.deleteBook(softDeleteBookRequest.convertToDeleteRequest());
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

//    @PutMapping("{id}/authorName/change/{name}")
//    public ResponseEntity<TCResponse<?>> updateNameOfAuthorByBook(
//            @PathVariable("id") String bookId, @PathVariable("name") String authorName) {
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.updateNameOfAuthorByBook(bookId, authorName))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }


//    @GetMapping("{authorID}/getBooks/by/authorName")
//    public ResponseEntity<TCResponse<?>> getBooksByAuthorName(@PathVariable("authorName") String authorName) {
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.getBooksByAuthorName(authorName))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

//    @PatchMapping("/update/book")
//    public ResponseEntity<TCResponse<?>> updateBook(
//            @RequestBody UpdateBookRequest updateBookRequest) {
//
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.updateBook(updateBookRequest))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }

//    @PatchMapping("/update/bookNameAndReleaseYear")
//    public ResponseEntity<TCResponse<?>> updateBookNameAndReleaseYear(
//            @RequestBody UpdateBookNameAndReleaseYearRequest request) {
//
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.updateBookNameAndReleaseYear(request))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
}
