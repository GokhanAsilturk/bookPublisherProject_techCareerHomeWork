package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.request.adminRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.adminRequests.DeleteAuthorRequest;
import bookPublisherProject.data.request.authorRequests.RegisterAuthorRequest;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;

import java.util.List;

public interface IAuthorService {

    AuthorDto createAuthor(CreateAuthorRequest createAuthorRequest);

    AuthorDto registerAuthor(RegisterAuthorRequest registerAuthorRequest);

    void deleteAuthor(DeleteAuthorRequest deleteAuthorRequest);

    void permanentlyDeleteAuthor(String id);

    void softDeleteAuthor(String id);

    List<AuthorDto> getAllAuthors();

    AuthorDto getAuthorById(String id);

    AuthorDto getAuthorByName(String authorName);

    List<BookDto> getBooksByAuthorName(String authorName);

    AuthorDto updateAuthor(UpdateAuthorRequest updateAuthorRequest);


    AuthorDto updateAuthorName(String authorId, String authorName);

    AuthorDto getAuthorByEmailAdress(String emailAddress);


}
