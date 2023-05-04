package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.bookRequests.CreateBookRequest;

import java.util.List;

public interface IBookService {

    BookDto createBook(CreateBookRequest createBookRequest);

    BookDto softDeleteBook(int id);

    BookDto permanentlyDeleteBook(int id);

    List<BookDto> getAllBooks();



}
