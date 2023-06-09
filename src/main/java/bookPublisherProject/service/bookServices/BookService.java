package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;
import bookPublisherProject.data.request.adminRequests.DeleteBookRequest;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.*;
import bookPublisherProject.exception.DataNotFoundException;
import bookPublisherProject.exception.ExceptionType;
import bookPublisherProject.service.authorServices.AuthorEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO null kontrolleri iptal edilecek
@Service
@RequiredArgsConstructor
public class BookService implements IBookService {


    private final BookEntityService bookEntityService;

    private final AuthorEntityService authorEntityService;


    @Override
    public BookDto createBook(CreateBookRequest createBookRequest) {


        AuthorEntity authorEntity = authorEntityService.getById(createBookRequest.authorId());
        //Yazarı belirterek kitabı ekliyoruz.
        BookEntity bookEntity = bookEntityService.create(createBookRequest.convertToEntity(authorEntity));

        return convertToDto(bookEntity);
    }


    @Override
    public BookDto createBookAndAuthor(CreateBookAndAuthorRequest createBookAndAuthorRequest) {

        BookEntity bookEntity = bookEntityService.create(createBookAndAuthorRequest.convertToEntity());
        AuthorEntity authorEntity = authorEntityService.save(bookEntity.getAuthorEntity());
        return convertToDto(bookEntity);
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
    public void softDeleteBook(String bookId) {
        //Nesnelere kopya alıyoruz
        BookEntity deletedBookEntity = this.bookEntityService.getById(bookId);
        AuthorEntity deletedAuthorEntity = deletedBookEntity.getAuthorEntity();

        //kitabı soft siliyoruz.
        bookEntityService.softDelete(deletedBookEntity);

        //Yazarın kitaplarını çağırıyoruz.
//        Optional<List<BookEntity>> bookEntityList = bookEntityService
//                .retrieveAllByAuthorId(deletedAuthorEntity.getId(), false);

        //silme işleminden sonra silinen kitabın yazarının başka bir kitabı kalmıyorsa, yazar da sistemden soft silinir.
        //eğer silinen kitabın yazarının başka kitabı yok ise yazarı soft sil.
        if (bookEntityService.retrieveAllByAuthorId(deletedAuthorEntity.getId(), false).isPresent()) {
            List<BookEntity> bookEntityList = bookEntityService.retrieveAllByAuthorId(deletedAuthorEntity.getId(), false).get();
            if (bookEntityList.isEmpty()) {
                authorEntityService.softDelete(deletedAuthorEntity);
            }
        }
//        if (bookEntityService.retrieveAllByAuthorId(deletedAuthorEntity.getId(), false).get().isEmpty()) {
//            authorEntityService.softDelete(deletedAuthorEntity);
//        }

    }

    @Override
    public void permanentlyDeleteBook(String bookId) {

        BookEntity deletedBookEntity = bookEntityService.getById(bookId);
        bookEntityService.permanentlyDelete(deletedBookEntity);

        AuthorEntity authorEntity = authorEntityService.getById(deletedBookEntity.getAuthorEntity().getId());
        Optional<List<BookEntity>> bookEntityList = bookEntityService.retrieveAllByAuthorId(authorEntity.getId(), true);

        //silme işleminden sonra silinen kitabın yazarının başka bir kitabı kalmıyorsa, yazar da sistemden tamamen silinir.
        //eğer silinen kitabın yazarının başka kitabı yok ise yazarı tamamen sil.
        if (bookEntityList.isEmpty()) {
            this.authorEntityService.permanentlyDelete(authorEntity);
        }

    }

    //TODO buralarda sıkıntı var. exception handler düzgün çalışmıyor. 404hatası veriyor. getallauthors da vermiyor.
    @Override
    public List<BookDto> getAllBooks() {

        List<BookDto> bookDtoList = this.bookEntityService.getAll().stream().map(this::convertToDto).toList();
        if (bookDtoList.isEmpty()) {
            throw new DataNotFoundException(ExceptionType.BOOK_LIST_NOT_FOUND, "Book List is Empty! :D");
        } else {
            return bookDtoList;
        }
    }

    @Override
    public BookDto getBookById(String id) {
        return this.convertToDto(bookEntityService.getById(id));
    }

    @Override
    public List<BookDto> getBooksByAuthorName(String authorName) {

        AuthorEntity authorEntity = authorEntityService.getByName(authorName);

        Optional<List<BookEntity>> bookEntityList = bookEntityService.retrieveAllByAuthorId(authorEntity.getId(), false);

        return bookEntityList.get().stream().map(BookEntity::convertToDto).toList();

    }

    @Override
    public BookDto updateBook(UpdateBookRequest updateBookRequest) {
        BookEntity bookEntity = bookEntityService.getById(updateBookRequest.bookId());

        BookEntity updatedBookEntity = this.bookEntityService.update(updateBookRequest.convertToEntity(bookEntity));

        return this.convertToDto(updatedBookEntity);
    }

    @Override
    public BookDto updateBookAndAuthor(UpdateBookAndAuthorRequest updateBookAndAuthorRequest) {

        BookEntity bookEntity = bookEntityService.getById(updateBookAndAuthorRequest.updateBookRequest().bookId());

        //kitabı, yazarını da katarak update ediyoruz.
        BookEntity updatedBookEntity = bookEntityService.update(updateBookAndAuthorRequest.convertToBookEntity(bookEntity));
        // Yazarı update ediyoruz
        authorEntityService.update(updatedBookEntity.getAuthorEntity());

        return this.convertToDto(updatedBookEntity);
    }

    @Override
    public BookDto updateNameOfAuthorByBook(String bookId, String newAuthorName) {

        BookEntity bookEntity = bookEntityService.getById(bookId);

        //DB de bulunan yazarın adını güncellerken kopya nesne alıyoruz.
        AuthorEntity updatedAuthorEntity = authorEntityService.updateName(bookEntity.getAuthorEntity(), newAuthorName);

        // soft delete yapılanlar dahil, yazarın bütün kitaplarını getiriyoruz.
        Optional<List<BookEntity>> bookEntityList = bookEntityService.retrieveAllByAuthorId(updatedAuthorEntity.getId(), true);

        //Kitap listesi boş değil ise yazarın bütün kitaplarını güncelliyoruz.
        bookEntityList.ifPresent(bookEntities -> bookEntities.forEach(book -> {
            book.setAuthorEntity(updatedAuthorEntity);
            bookEntityService.update(book);
        }));


        return this.convertToDto(bookEntity);
    }

    @Override
    public BookDto updateBookNameAndReleaseYear(UpdateBookNameAndReleaseYearRequest updateBookNameAndReleaseYearRequest) {

        BookEntity updatedBookEntity = this.bookEntityService.update(updateBookNameAndReleaseYearRequest.convertToEntity(bookEntityService.getById(updateBookNameAndReleaseYearRequest.bookId())));

        return convertToDto(updatedBookEntity);
    }

    @Override
    public BookDto publishNewBook(PublishNewBookRequest publishNewBookRequest) {

        BookEntity bookEntity = publishNewBookRequest.convertToEntity(authorEntityService.getById(publishNewBookRequest.authorId()));

        return convertToDto(this.bookEntityService.publish(bookEntity));
    }

    public BookDto convertToDto(BookEntity bookEntity) {

        return bookEntity.convertToDto();
    }
}
