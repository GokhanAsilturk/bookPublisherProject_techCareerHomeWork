package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static bookPublisherProject.data.mapper.AuthorMapper.AUTHOR_MAPPER;
import static bookPublisherProject.data.mapper.BookMapper.BOOK_MAPPER;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {


    private final BookEntityService bookEntityService;


    @Override
    public BookDto createBook(CreateBookRequest createBookRequest) {
        return null;
    }

    @Override
    public BookDto createBookAndAuthor(CreateBookAndAuthorRequest createBookAndAuthorRequest) {

        return this.convertToDto(
                this.bookEntityService.save(BOOK_MAPPER.createBookAndAuthor(createBookAndAuthorRequest)));
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
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public BookDto updateBook(UpdateBookRequest updateBookRequest) {

        return this.convertToDto(this.bookEntityService.update(
                updateBookRequest.bookId(),
                updateBookRequest.newBookName(),
                updateBookRequest.newDescription(),
                updateBookRequest.newReleaseDate()
        ));
    }

    @Override
    public BookDto updateBookAndAuthor(UpdateBookAndAuthorRequest updateBookAndAuthorRequest) {

        return this.convertToDto(this.bookEntityService.updateBookAndAuthor(
                updateBookAndAuthorRequest.updateBookRequest().bookId(),
                BOOK_MAPPER.updateBook(updateBookAndAuthorRequest.updateBookRequest()),
                AUTHOR_MAPPER.updateAuthor(updateBookAndAuthorRequest.updateAuthorRequest())));
    }

    @Override
    public BookDto updateNameOfAuthorByBook(int bookId, String authorName) {
        return this.convertToDto(this.bookEntityService.updateNameOfAuthorByBook(bookId, authorName));
    }

    @Override
    public BookDto updateBookNameAndReleaseYear(UpdateBookNameAndReleaseYearRequest updateBookNameAndReleaseYearRequest) {
        return convertToDto(this.bookEntityService.updateBookNameAndReleaseYear(
                updateBookNameAndReleaseYearRequest.bookName()
                , updateBookNameAndReleaseYearRequest.releaseYear()));
    }

    @Override
    public BookDto publishNewBook(PublishNewBookRequest publishNewBookRequest) {
        return null;
    }

    public BookDto convertToDto(Book book) {
        return BOOK_MAPPER.convertToBookDto(book);
    }
}
