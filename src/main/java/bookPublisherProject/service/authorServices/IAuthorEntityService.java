package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;

import java.util.List;

public interface IAuthorEntityService {

    Author save(Author author);

    Author permanentlyDelete(int id);
    Author softDelete(int id);

    List<Author> getAll();

    Author getById(int id);

    Author getByName(String name);

    List<Book> getBooksByName(String name);

    Author update(int authorId, String newName, String newEmailAddress, String newBio);

    Author updateName(int id, String name);

}
