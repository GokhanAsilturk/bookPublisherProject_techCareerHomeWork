package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;

import java.util.List;

public interface IAuthorService {

    AuthorDto createAuthor(CreateAuthorRequest createAuthorRequest);

    AuthorDto permanentlyDeleteAuthor(int id);
    AuthorDto softDeleteAuthor(int id);

    List<AuthorDto> getAllAuthors();

    AuthorDto getAuthorById(int id);


}
