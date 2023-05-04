package bookPublisherProject.controller;

import bookPublisherProject.data.request.bookRequests.CreateBookRequest;
import bookPublisherProject.data.response.TCResponse;
import bookPublisherProject.service.bookServices.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

//    @PostMapping("/create/book")
//    public ResponseEntity<TCResponse<?>> createBook(@RequestBody CreateBookRequest createBookRequest) {
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.createBook(createBookRequest))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @DeleteMapping("{id}/delete/book")
//    public ResponseEntity<TCResponse<?>> deleteBook(@PathVariable("id") int id) {
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.deleteBook(id))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @PutMapping("{id}/author/change/{name}")
//    public ResponseEntity<TCResponse<?>> updateNameOfAuthorByBook(@PathVariable("id") int id, @PathVariable("name") String bookName) {
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.updateNameOfAuthorByBook(id, bookName))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//
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
//
//
//    @GetMapping("{authorID}/getBooks/by/authorID")
//    public ResponseEntity<TCResponse<?>> getBookByAuthorID(@PathVariable("authorID") String authorID) {
//        try {
//            return ResponseEntity.ok(TCResponse.builder()
//                    .isSuccess(true)
//                    .response(bookService.getBookByAuthorId(authorID))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @PatchMapping("/update/bookNameAndReleaseYear")
//    public ResponseEntity<TCResponse<?>> updateBookNameAndReleaseYear(@RequestBody UpdateBookNameAndReleaseYearRequest request) {
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
