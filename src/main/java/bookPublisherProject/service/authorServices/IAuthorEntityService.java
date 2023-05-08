package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;

import java.util.List;

public interface IAuthorEntityService {

    Author save(Author author);

    void permanentlyDelete(String id);
    Author softDelete(String id);

    List<Author> getAll();

    Author getById(String id);

    Author getByName(String name);

    List<Book> getBooksByName(String name);

    Author update(String authorId, String newName, String newEmailAddress, String newBio);

    Author updateName(String id, String name);

}
