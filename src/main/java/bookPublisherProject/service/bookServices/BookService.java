package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;
import bookPublisherProject.data.request.adminRequests.DeleteBookRequest;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.*;
import bookPublisherProject.service.authorServices.AuthorEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {


    private final BookEntityService bookEntityService;

    private final AuthorEntityService authorEntityService;


    @Override
    public BookDto createBook(CreateBookRequest createBookRequest) {


        AuthorEntity authorEntity = authorEntityService.getById(createBookRequest.authorId());
        //Yazarı belirterek kitabı ekliyoruz.
        BookEntity bookEntity = bookEntityService.create(
                createBookRequest.convertToEntity(authorEntity)
        );

        return convertToDto(bookEntity);
    }

    @Override
    public BookDto createBookAndAuthor(CreateBookAndAuthorRequest createBookAndAuthorRequest) {

        BookEntity bookEntity = bookEntityService.create(createBookAndAuthorRequest.convertToEntity());

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
        Optional<List<BookEntity>> bookEntityList = bookEntityService
                .retrieveAllByAuthorId(deletedAuthorEntity.getId(), false);

        //silme işleminden sonra silinen kitabın yazarının başka bir kitabı kalmıyorsa, yazar da sistemden soft silinir.
        //eğer silinen kitabın yazarının başka kitabı yok ise yazarı soft sil.
        if (bookEntityList.isEmpty()) {
            authorEntityService.softDelete(deletedAuthorEntity);
        }

    }

    @Override
    public void permanentlyDeleteBook(String bookId) {

        BookEntity deletedBookEntity = bookEntityService.getById(bookId);
        bookEntityService.permanentlyDelete(deletedBookEntity);

        AuthorEntity authorEntity = authorEntityService.getById(deletedBookEntity.getAuthorEntity().getId());
        Optional<List<BookEntity>> bookEntityList = bookEntityService
                .retrieveAllByAuthorId(authorEntity.getId(), true);

        //silme işleminden sonra silinen kitabın yazarının başka bir kitabı kalmıyorsa, yazar da sistemden tamamen silinir.
        //eğer silinen kitabın yazarının başka kitabı yok ise yazarı tamamen sil.
        if (bookEntityList.isEmpty()) {
            this.authorEntityService.permanentlyDelete(authorEntity);
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

        AuthorEntity authorEntity = authorEntityService.getByName(authorName);
        Optional<List<BookEntity>> bookEntityList = bookEntityService
                .retrieveAllByAuthorId(authorEntity.getId(), false);

        if (bookEntityList.isPresent()) {
            return bookEntityList.get()
                    .stream()
                    .map(BookEntity::convertToDto).toList();
        }
        return List.of();

    }

    @Override
    public BookDto updateBook(UpdateBookRequest updateBookRequest) {

        return this.convertToDto(this.bookEntityService
                .update(updateBookRequest.convertToEntity(bookEntityService.getById(updateBookRequest.bookId()))));
    }

    @Override
    public BookDto updateBookAndAuthor(UpdateBookAndAuthorRequest updateBookAndAuthorRequest) {
        //kitabı update ediyoruz.
        BookEntity bookEntity = bookEntityService.update(
                updateBookAndAuthorRequest.convertToBookEntity(
                        bookEntityService.getById(updateBookAndAuthorRequest.updateBookRequest().bookId())));

        //yazarı, kitaba kaydederken, yazarı update ediyoruz.
        bookEntity.setAuthorEntity(authorEntityService.update(updateBookAndAuthorRequest.convertToAuthorEntity()));

        return this.convertToDto(bookEntity);
    }

    @Override
    public BookDto updateNameOfAuthorByBook(String bookId, String newAuthorName) {

        BookEntity bookEntity = bookEntityService.getById(bookId);
        //DB de bulunan yazarın adını güncellerken kopya nesne alıyoruz.
        AuthorEntity updatedAuthorEntity = authorEntityService.updateName(
                bookEntity.getAuthorEntity(), newAuthorName);

        // soft delete yapılanlar dahil, yazarın bütün kitaplarını getiriyoruz.
        Optional<List<BookEntity>> bookEntityList = bookEntityService
                .retrieveAllByAuthorId(updatedAuthorEntity.getId(), true);

        //Kitap listesi boş değil ise yazarın bütün kitaplarını güncelliyoruz.
        bookEntityList.ifPresent(bookEntities -> bookEntities.forEach(book -> {
            book.setAuthorEntity(updatedAuthorEntity);
            bookEntityService.update(book);
        }));


        return this.convertToDto(bookEntity);
    }

    @Override
    public BookDto updateBookNameAndReleaseYear(UpdateBookNameAndReleaseYearRequest updateBookNameAndReleaseYearRequest) {

        return this.convertToDto(this.bookEntityService
                .update(updateBookNameAndReleaseYearRequest.convertToEntity(
                        bookEntityService.getById(updateBookNameAndReleaseYearRequest.bookId()))));
    }

    @Override
    public BookDto publishNewBook(PublishNewBookRequest publishNewBookRequest) {

        BookEntity bookEntity = publishNewBookRequest.convertToEntity(
                authorEntityService.getById(publishNewBookRequest.authorId()));

        return convertToDto(this.bookEntityService.publish(bookEntity));
    }

    public BookDto convertToDto(BookEntity bookEntity) {

        return bookEntity.convertToDto();
    }
}
