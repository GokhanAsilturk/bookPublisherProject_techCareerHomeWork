package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.*;

import java.util.List;

public interface IBookService {

    BookDto createBook(CreateBookRequest createBookRequest);
    BookDto createBookAndAuthor(CreateBookAndAuthorRequest createBookAndAuthorRequest);

    BookDto deleteBook(DeleteBookRequest deleteBookRequest);

    BookDto softDeleteBook(int id);

    BookDto permanentlyDeleteBook(int id);

    List<BookDto> getAllBooks();

    BookDto getBookById(int id);

    List<BookDto> getBooksByAuthorName(String authorName);

    BookDto updateBook(UpdateBookRequest updateBookRequest);

    BookDto updateBookAndAuthor(UpdateBookAndAuthorRequest updateBookAndAuthorRequest);

    BookDto updateNameOfAuthorByBook(int bookId, String authorName);

    BookDto updateBookNameAndReleaseYear(UpdateBookNameAndReleaseYearRequest updateBookNameAndReleaseYearRequest);

    BookDto publishNewBook(PublishNewBookRequest publishNewBookRequest);

}
