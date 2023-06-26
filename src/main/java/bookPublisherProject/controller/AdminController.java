package bookPublisherProject.controller;

import bookPublisherProject.data.request.adminRequests.CreateAdminRequest;
import bookPublisherProject.data.request.adminRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.adminRequests.DeleteAuthorRequest;
import bookPublisherProject.data.request.adminRequests.DeleteBookRequest;
import bookPublisherProject.data.request.bookRequests.CreateBookAndAuthorRequest;
import bookPublisherProject.data.request.bookRequests.CreateBookRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookNameAndReleaseYearRequest;
import bookPublisherProject.data.types.response.TCResponse;
import bookPublisherProject.service.adminServices.AdminService;
import bookPublisherProject.service.authorServices.AuthorService;
import bookPublisherProject.service.bookServices.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {


    private final AuthorService authorService;

    private final BookService bookService;

    private final AdminService adminService;

    @Autowired
    public AdminController(AuthorService authorService, BookService bookService, AdminService adminService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.adminService = adminService;
    }


    @PostMapping("/createAdmin")
    public ResponseEntity<TCResponse<?>> createAdmin(@RequestBody CreateAdminRequest createAdminRequest) {
        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(this.adminService.createAdmin(createAdminRequest))
                .build());
    }

    @GetMapping("/getDetailedBookById{id}")
    public ResponseEntity<TCResponse<?>> getDetailedBookById(
            @RequestParam("id") String id) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(adminService.getDetailedBookById(id))
                .build());
    }

    @GetMapping("/getAdminByEmailAddress{emailAddress}")
    public ResponseEntity<TCResponse<?>> getAdminByEmailAddress(
            @RequestParam("emailAddress") String emailAddress) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(adminService.getAdminByEmailAddress(emailAddress))
                .build());
    }

    @GetMapping("/getAdminDetailedByEmailAddress{emailAddress}")
    public ResponseEntity<TCResponse<?>> getAdminDetailedByEmailAddress(
            @RequestParam("emailAddress") String emailAddress) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(adminService.getAdminDetailedByEmailAddress(emailAddress))
                .build());
    }


    @GetMapping("/getAllAdmins")
    public ResponseEntity<TCResponse<?>> getAllAdmins() {
        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(adminService.getAllAdmins())
                .build());
    }

    @GetMapping("/getAllAdminsDetailed")
    public ResponseEntity<TCResponse<?>> getAllAdminsDetailed() {
        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(adminService.getAllAdminsDetailed())
                .build());
    }

    //    1-) API, şirketin veritabanına yeni bir kitap kaydı oluşturmasına izin vermeli ve bu kayıt, kitabın başlığı,
    //açıklaması, yayın tarihi ve yazar bilgilerini (ad, e-posta ve bio) içermelidir.
    @PostMapping("/create/bookAndAuthor")
    public ResponseEntity<TCResponse<?>> createBookAndAuthor(@RequestBody CreateBookAndAuthorRequest createBookAndAuthorRequest) {
        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(bookService.createBookAndAuthor(createBookAndAuthorRequest))
                .build());

    }

    @PostMapping("/create/author")
    public ResponseEntity<TCResponse<?>> createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(this.authorService.createAuthor(createAuthorRequest))
                .build());

    }

    @PostMapping("/create/book")
    public ResponseEntity<TCResponse<?>> createBook(@RequestBody CreateBookRequest createBookRequest) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(this.bookService.createBook(createBookRequest))
                .build());

    }

    @DeleteMapping("/delete/author")
    public ResponseEntity<TCResponse<?>> deleteAuthor(@RequestBody DeleteAuthorRequest deleteAuthorRequest) {

        this.authorService.deleteAuthor(deleteAuthorRequest);
        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .build());

    }

    @DeleteMapping("/delete/book")
    public ResponseEntity<TCResponse<?>> deleteBook(@RequestBody DeleteBookRequest deleteBookRequest) {
        this.bookService.deleteBook(deleteBookRequest);

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .build());

    }

    @PutMapping("{bookId}/authorName/change/{authorName}")
    public ResponseEntity<TCResponse<?>> updateNameOfAuthorByBook(
            @RequestParam("bookId") String bookId, @RequestParam("authorName") String authorName) {

        return ResponseEntity.ok(TCResponse.builder()
                .isSuccess(true)
                .response(bookService.updateNameOfAuthorByBook(bookId, authorName))
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


}
