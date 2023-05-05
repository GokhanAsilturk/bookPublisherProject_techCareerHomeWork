package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.bookRequests.CreateBookRequest;
import bookPublisherProject.data.request.bookRequests.DeleteBookRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookNameAndReleaseYearRequest;

import java.util.List;

public interface IBookService {

    BookDto createBook(CreateBookRequest createBookRequest);

    BookDto deleteBook(DeleteBookRequest deleteBookRequest);

    BookDto softDeleteBook(int id);

    BookDto permanentlyDeleteBook(int id);

    List<BookDto> getAllBooks();

    BookDto getBookById(int id);

    List<BookDto> getBooksByAuthorName(String authorName);

    BookDto updateNameOfAuthorByBook(int bookId, String authorName);

    BookDto updateBookNameAndReleaseYear(UpdateBookNameAndReleaseYearRequest updateBookNameAndReleaseYearRequest);



}
