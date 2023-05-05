package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.bookRequests.CreateBookRequest;
import bookPublisherProject.data.request.bookRequests.DeleteBookRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookNameAndReleaseYearRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static bookPublisherProject.data.mapper.BookMapper.BOOK_MAPPER;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {


    private final BookEntityService bookEntityService;


    @Override
    public BookDto createBook(CreateBookRequest createBookRequest) {

        return this.convertToDto(
                this.bookEntityService.save(BOOK_MAPPER.createBook(createBookRequest)));
    }

    //silme işlemini nasıl yapacağımıza karar veriyoruz.
    @Override
    public BookDto deleteBook(DeleteBookRequest deleteBookRequest) {

        if (deleteBookRequest.permanentlyDelete()) {
            return this.permanentlyDeleteBook(deleteBookRequest.id());
        }
        return this.softDeleteBook(deleteBookRequest.id());
    }

    @Override
    public BookDto softDeleteBook(int id) {

        return this.convertToDto(this.bookEntityService.softDelete(id));
    }

    @Override
    public BookDto permanentlyDeleteBook(int id) {
        return this.convertToDto(this.bookEntityService.permanentlyDelete(id));
    }

    @Override
    public List<BookDto> getAllBooks() {
        return this.bookEntityService.getAll()
                .stream()
                .map(this::convertToDto).toList();
    }

    @Override
    public BookDto getBookById(int id) {
        return this.convertToDto(this.bookEntityService.getById(id));
    }

    @Override
    public List<BookDto> getBooksByAuthorName(String authorName) {
        return this.bookEntityService.getByAuthorName(authorName)
                .stream()
                .map(BOOK_MAPPER::convertToBookDto).toList();
    }

    @Override
    public BookDto updateNameOfAuthorByBook(int bookId, String authorName) {
        return this.convertToDto(this.bookEntityService.updateNameOfAuthorByBook(bookId, authorName));
    }

    @Override
    public BookDto updateBookNameAndReleaseYear(UpdateBookNameAndReleaseYearRequest updateBookNameAndReleaseYearRequest) {
        return convertToDto(this.bookEntityService.updateBookNameAndReleaseYear(
                updateBookNameAndReleaseYearRequest.name()
                ,updateBookNameAndReleaseYearRequest.releaseYear()));
    }

    public BookDto convertToDto(Book book) {
        return BOOK_MAPPER.convertToBookDto(book);
    }
}
