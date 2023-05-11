package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;

import java.util.List;

public interface IAuthorEntityService {

    Author save(Author author);

    Author register(Author author);

    void permanentlyDelete(Author author);

    void softDelete(Author author);

    List<Author> getAll();

    Author getById(String id);

    Author getByName(String name);

    List<Book> getBooksByName(String name);

    Author update(Author author);

    Author updateName(Author author, String name);

}
