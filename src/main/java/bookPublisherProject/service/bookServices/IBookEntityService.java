package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;

import java.util.List;

public interface IBookEntityService {

    Book save(Book book);

    Book softDelete(int id);
    Book permanentlyDelete(int id);

    List<Book> getAll();

}
