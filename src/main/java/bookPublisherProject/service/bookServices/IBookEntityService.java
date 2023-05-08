package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookAndAuthorRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookNameAndReleaseYearRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookRequest;

import java.util.List;

public interface IBookEntityService {

    Book create(Book book);

    void softDelete(Book book);

    void permanentlyDelete(String id);

    List<Book> getAll();

    Book getById(String id);

    Book update(Book book);

    Book publish(Book book);
}
