package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.authorRequests.DeleteAuthorRequest;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;

import java.util.List;

public interface IAuthorService {

    AuthorDto createAuthor(CreateAuthorRequest createAuthorRequest);

    AuthorDto deleteAuthor(DeleteAuthorRequest deleteAuthorRequest);

    AuthorDto permanentlyDeleteAuthor(int id);

    AuthorDto softDeleteAuthor(int id);

    List<AuthorDto> getAllAuthors();

    AuthorDto getAuthorById(int id);

    AuthorDto getAuthorByName(String authorName);

    List<BookDto> getBooksByAuthorName(String authorName);

    AuthorDto updateAuthor(UpdateAuthorRequest updateAuthorRequest);


    AuthorDto updateAuthorName(int authorId, String authorName);


}
