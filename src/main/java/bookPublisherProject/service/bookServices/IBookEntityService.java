package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookNameAndReleaseYearRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookRequest;

import java.util.List;

public interface IBookEntityService {

    Book save(Book book);

    Book softDelete(int id);

    Book permanentlyDelete(int id);

    List<Book> getAll();

    Book getById(int id);

    List<Book> getByAuthorName(String authorName);

    Book update(int id, String newBookName, String newDescription, String newReleaseDate);

    Book updateBookAndAuthor(int BookId, Book newBook, Author newAuthor);
    Book updateNameOfAuthorByBook(int bookId, String authorName);

    Book updateBookNameAndReleaseYear(String bookName , String releaseYear);

    Book publish(PublishNewBookRequest publishNewBookRequest);
}
