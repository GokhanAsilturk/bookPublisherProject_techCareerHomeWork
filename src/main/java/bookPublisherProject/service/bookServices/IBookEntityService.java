package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.entity.Book;

import java.util.List;

public interface IBookEntityService {

    Book create(Book book);

    void softDelete(Book book);

    void permanentlyDelete(Book book);

    List<Book> getAll();

    Book getById(String id);

    Book update(Book book);

    Book publish(Book book);
}
