package bookPublisherProject.controller;

import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.response.TCResponse;
import bookPublisherProject.service.authorServices.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final AuthorService authorService;

    @Autowired
    public AdminController(AuthorService authorService) {
        this.authorService = authorService;
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


}
