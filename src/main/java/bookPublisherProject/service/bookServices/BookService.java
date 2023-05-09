package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.*;
import bookPublisherProject.service.authorServices.AuthorEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {


    private final BookEntityService bookEntityService;

    private final AuthorEntityService authorEntityService;


    @Override
    public BookDto createBook(CreateBookRequest createBookRequest) {

        //Yazarı belirterek kitabı ekliyoruz.
        Book book = bookEntityService.create(
                createBookRequest.convertToEntity(
                        authorEntityService.getById(createBookRequest.authorId())));

        //Kitap eklendikten sonra, yazarın kitap listesine de kitabı ekliyoruz.
        authorEntityService.addBookInBookListAndUpdate(authorEntityService.getById(createBookRequest.authorId())
                , book);

        return this.convertToDto(book);
    }

    @Override
    public BookDto createBookAndAuthor(CreateBookAndAuthorRequest createBookAndAuthorRequest) {

        Book book = bookEntityService.create(createBookAndAuthorRequest.convertToEntity());

        //Kitapla beraber oluşturduğumuz yazarı kaydederken, yazarın books listesine oluşturduğumuz kitabı ekliyoruz.
        authorEntityService.addBookInBookListAndUpdate(book.getAuthor(), book);

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

        return this.convertToDto(this.bookEntityService
                .update(updateBookRequest.convertToEntity(bookEntityService.getById(updateBookRequest.bookId()))));
    }

    @Override
    public BookDto updateBookAndAuthor(UpdateBookAndAuthorRequest updateBookAndAuthorRequest) {
        //kitabı, yazarı ekleyerek update ediyoruz.
        Book book = bookEntityService.update(
                updateBookAndAuthorRequest.convertToBookEntity(
                        bookEntityService.getById(updateBookAndAuthorRequest.updateBookRequest().bookId())));

        //yazarı update ediyoruz.
        this.authorEntityService.update(book.getAuthor());

        return this.convertToDto(book);
    }

    @Override
    public BookDto updateNameOfAuthorByBook(String bookId, String newAuthorName) {

        //DB de bulunan yazarın adını güncelliyoruz.
        Author author = authorEntityService.updateName(
                bookEntityService.getById(bookId).getAuthor(), newAuthorName);

        author.getBooks().forEach(book -> {//Yazarın kitaplarını geziyoruz.
            book.setAuthor(author);//Yazarın kitap listesindeki kitapların, yazarlarını değiştiriyoruz.
            bookEntityService.update(book);//DB deki kitapları güncelliyoruz.
        });

        return this.convertToDto(this.bookEntityService.getById(bookId));
    }

    @Override
    public BookDto updateBookNameAndReleaseYear(UpdateBookNameAndReleaseYearRequest updateBookNameAndReleaseYearRequest) {

        return this.convertToDto(this.bookEntityService
                .update(updateBookNameAndReleaseYearRequest.convertToEntity(
                        bookEntityService.getById(updateBookNameAndReleaseYearRequest.bookId()))));
    }

    @Override
    public BookDto publishNewBook(PublishNewBookRequest publishNewBookRequest) {

        Book book = publishNewBookRequest.convertToEntity(
                authorEntityService.getById(publishNewBookRequest.authorId()));

        //hangi yazar girildiyse, o yazarın kitaplar listesine kitabı ekliyoruz.
        authorEntityService.addBookInBookListAndUpdate(
                authorEntityService.getById(publishNewBookRequest.authorId()), book);

        return convertToDto(this.bookEntityService.publish(book));
    }

    public BookDto convertToDto(Book book) {

        return book.convertToDto();
    }
}
