package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.*;
import bookPublisherProject.service.authorServices.AuthorEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static bookPublisherProject.data.mapper.AuthorMapper.AUTHOR_MAPPER;
import static bookPublisherProject.data.mapper.BookMapper.BOOK_MAPPER;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {


    private final BookEntityService bookEntityService;

    private final AuthorEntityService authorEntityService;


    @Override
    public BookDto createBook(CreateBookRequest createBookRequest) {

        Book book = createBookRequest.convertToEntity(authorEntityService.getById(createBookRequest.authorId()));

        //Kitap eklendikten sonra, yazarın kitap listesine de kitabı ekliyoruz.
        authorEntityService.addBookInBookList(authorEntityService.getById(createBookRequest.authorId())
                , bookEntityService.create(book));

        return this.convertToDto(book);
    }

    @Override
    public BookDto createBookAndAuthor(CreateBookAndAuthorRequest createBookAndAuthorRequest) {

        Book book = this.bookEntityService.create(BOOK_MAPPER
                .createBookAndAuthor(createBookAndAuthorRequest));

        //Kitaba yazar eklerken aynı zamanda yazarı kaydediyoruz.
        book.setAuthor(authorEntityService.save(AUTHOR_MAPPER
                .createAuthor(createBookAndAuthorRequest.authorRequest())));
        //Kitapla beraber oluşturduğumuz ve kitabın içerisine kaydettiğimiz yazarı, yazar reposuna da eklerken
        //yazarın books listesine oluşturduğumuz kitabı ekliyoruz.
        authorEntityService.save(authorEntityService.addBookInBookList(book.getAuthor(), book));

        return this.convertToDto(book);
    }

    //silme işlemini nasıl yapacağımıza karar veriyoruz.
    @Override
    public void deleteBook(DeleteBookRequest deleteBookRequest) {

        if (deleteBookRequest.permanentlyDelete()) {
            this.permanentlyDeleteBook(deleteBookRequest.id());
        } else {
            this.softDeleteBook(deleteBookRequest.id());
        }

    }

    @Override
    public void softDeleteBook(String id) {
        Book deletedBook = this.bookEntityService.getById(id);

        //silme işleminden sonra silinen kitabın yazarının başka bir kitabı kalmıyorsa, yazar da sistemden silinir.
        //kitapları gez ve yazarlarına bak. eğer silinen kitabın yazarı ile eşleşen bir yazar yok ise yazarı sil.
        if (!this.bookEntityService.getAll()
                .stream()
                .map(Book::getAuthor)
                .toList()
                .contains(deletedBook.getAuthor())) {
            this.authorEntityService.softDelete(deletedBook.getAuthor().getId());
        } else {
            this.bookEntityService.softDelete(this.bookEntityService.getById(id));
        }
    }

    @Override
    public void permanentlyDeleteBook(String id) {
        //Nesneye kopya alıyoruz
        Book deletedBook = this.bookEntityService.getById(id);

        //silme işleminden sonra silinen kitabın yazarının başka bir kitabı kalmıyorsa, yazar da sistemden tamamen silinir.
        //kitapları gez ve yazarlarına bak. eğer silinen kitabın yazarı ile eşleşen bir yazar yok ise yazarı tamamen sil.
        if (!this.bookEntityService.getAll()
                .stream()
                .map(Book::getAuthor)
                .toList()
                .contains(deletedBook.getAuthor())) {
            this.authorEntityService.permanentlyDelete(deletedBook.getAuthor().getId());
        } else {
            this.bookEntityService.permanentlyDelete(id);
        }

    }

    @Override
    public List<BookDto> getAllBooks() {
        return this.bookEntityService.getAll()
                .stream()
                .map(this::convertToDto).toList();
    }

    @Override
    public BookDto getBookById(String id) {
        return this.convertToDto(this.bookEntityService.getById(id));
    }

    @Override
    public List<BookDto> getBooksByAuthorName(String authorName) {
        return this.authorEntityService.getByName(authorName)
                .getBooks()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public BookDto updateBook(UpdateBookRequest updateBookRequest) {

        return this.convertToDto(this.bookEntityService.update(
                BOOK_MAPPER.updateBook(updateBookRequest,
                        bookEntityService.getById(updateBookRequest.bookId())
                )));
    }

    @Override
    public BookDto updateBookAndAuthor(UpdateBookAndAuthorRequest updateBookAndAuthorRequest) {
        Book book = BOOK_MAPPER.updateBook(updateBookAndAuthorRequest.updateBookRequest(),
                bookEntityService.getById(updateBookAndAuthorRequest.updateBookRequest().bookId())
        );

        book.setAuthor(AUTHOR_MAPPER.updateAuthor(updateBookAndAuthorRequest.updateAuthorRequest()));
        return this.convertToDto(this.bookEntityService.update(book));
    }

    @Override
    public BookDto updateNameOfAuthorByBook(String bookId, String authorName) {
        Book book = this.bookEntityService.getById(bookId);
        Author author = book.getAuthor();
        author.setName(authorName);
        book.setAuthor(author);
        return this.convertToDto(this.bookEntityService.update(book));
    }

    @Override
    public BookDto updateBookNameAndReleaseYear(UpdateBookNameAndReleaseYearRequest updateBookNameAndReleaseYearRequest) {

        return this.convertToDto(this.bookEntityService
                .update(BOOK_MAPPER.updateBookNameAndReleaseYear(updateBookNameAndReleaseYearRequest,
                        bookEntityService.getById(updateBookNameAndReleaseYearRequest.bookId()))));
    }

    @Override
    public BookDto publishNewBook(PublishNewBookRequest publishNewBookRequest) {


        //BOOK_MAPPER.publishBook, CreateBookRequest döndürdüğü için, BOOK_MAPPER.createBook kullandık.
        Book book = BOOK_MAPPER.createBook(BOOK_MAPPER.publishBook(publishNewBookRequest));

        //Yazarı kitaba eklerken, hangi yazar girildiyse, o yazarın kitaplar listesine kitabı ekliyoruz.
        book.setAuthor(authorEntityService
                .addBookInBookList(authorEntityService.getById(publishNewBookRequest.authorId()), book));

        return convertToDto(this.bookEntityService.publish(book));
    }

    public BookDto convertToDto(Book book) {

        AuthorDto authorDto = AUTHOR_MAPPER.convertToAuthorDto(book.getAuthor());

        return BOOK_MAPPER.convertToBookDto(book);
    }
}
