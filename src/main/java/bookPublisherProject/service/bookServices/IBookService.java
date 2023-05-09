package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.*;

import java.util.List;

public interface IBookService {

    BookDto createBook(CreateBookRequest createBookRequest);

    BookDto createBookAndAuthor(CreateBookAndAuthorRequest createBookAndAuthorRequest);

    void deleteBook(DeleteBookRequest deleteBookRequest);

    void softDeleteBook(String id);

    void permanentlyDeleteBook(String id);

    List<BookDto> getAllBooks();

    BookDto getBookById(String id);

    List<BookDto> getBooksByAuthorName(String authorName);

    BookDto updateBook(UpdateBookRequest updateBookRequest);

    BookDto updateBookAndAuthor(UpdateBookAndAuthorRequest updateBookAndAuthorRequest);

    BookDto updateNameOfAuthorByBook(String bookId, String authorName);

    BookDto updateBookNameAndReleaseYear(UpdateBookNameAndReleaseYearRequest updateBookNameAndReleaseYearRequest);

    BookDto publishNewBook(PublishNewBookRequest publishNewBookRequest);

}
