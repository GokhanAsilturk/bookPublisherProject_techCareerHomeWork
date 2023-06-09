package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;
import bookPublisherProject.data.request.adminRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.adminRequests.DeleteAuthorRequest;
import bookPublisherProject.data.request.authorRequests.RegisterAuthorRequest;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;
import bookPublisherProject.exception.DataNotFoundException;
import bookPublisherProject.exception.ExceptionType;
import bookPublisherProject.service.bookServices.BookEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

    private final AuthorEntityService authorEntityService;
    private final BookEntityService bookEntityService;

    @Override
    public AuthorDto createAuthor(CreateAuthorRequest createAuthorRequest) {

        return this.authorEntityService
                .save(createAuthorRequest.convertToEntity()).convertToDto();
    }

    @Override
    public AuthorDto registerAuthor(RegisterAuthorRequest registerAuthorRequest) {
        return authorEntityService.register(
                registerAuthorRequest.convertToCreateAuthorRequest().convertToEntity()).convertToDto();
    }

    @Override
    public void deleteAuthor(DeleteAuthorRequest deleteAuthorRequest) {

        if (deleteAuthorRequest.permanentlyDelete()) {
            this.permanentlyDeleteAuthor(deleteAuthorRequest.id());
        } else {
            this.softDeleteAuthor(deleteAuthorRequest.id());
        }
    }

    @Override
    public void permanentlyDeleteAuthor(String id) {

        AuthorEntity authorEntity = this.authorEntityService.getById(id);
        Optional<List<BookEntity>> bookEntityList =
                bookEntityService.retrieveAllByAuthorId(authorEntity.getId(), true);

        //Sistemden tamamen silinecek yazarın bütün kitaplarını da kalıcı olarak siliyoruz.
        bookEntityList.ifPresent(bookEntities ->
                bookEntities.forEach(this.bookEntityService::permanentlyDelete));

        this.authorEntityService.permanentlyDelete(authorEntity);
    }

    @Override
    public void softDeleteAuthor(String id) {

        AuthorEntity authorEntity = this.authorEntityService.getByIdAndIsDeletedFalse(id);
        Optional<List<BookEntity>> bookEntityList =
                bookEntityService.retrieveAllByAuthorId(authorEntity.getId(), false);

        //Sistemden soft silinecek yazarın bütün kitaplarını da soft siliyoruz.
        bookEntityList.ifPresent(bookEntities -> bookEntities.forEach(this.bookEntityService::softDelete));

        this.authorEntityService.softDelete(authorEntity);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {

        List<AuthorDto> authorEntities = this.authorEntityService.getAll().stream()
                .map(AuthorEntity::convertToDto)
                .toList();
        if (authorEntities.isEmpty()) {
            throw new DataNotFoundException(ExceptionType.AUTHOR_LIST_NOT_FOUND, "Author List is Empty! :D");
        } else {
            return authorEntities;
        }
    }

    @Override
    public AuthorDto getAuthorById(String id) {
        AuthorEntity authorEntity = authorEntityService.getById(id);

        return authorEntity.convertToDto();
    }

    @Override
    public AuthorDto getAuthorByName(String authorName) {
        AuthorEntity authorEntity = authorEntityService.getByName(authorName);

        return authorEntity.convertToDto();
    }

    @Override
    public List<BookDto> getBooksByAuthorName(String authorName) {

        return this.authorEntityService.getBooksByName(authorName)
                .stream()
                .map(BookEntity::convertToDto)
                .toList();
    }

    //TODO burada kaldım
    @Override
    public AuthorDto updateAuthor(UpdateAuthorRequest updateAuthorRequest) {
        AuthorEntity authorEntity = authorEntityService.getById(updateAuthorRequest.authorId());

        //yazarı update ediyoruz
        AuthorEntity updatedAuthorEntity = authorEntityService.update(updateAuthorRequest.convertToEntity());

        Optional<List<BookEntity>> bookEntityList =
                bookEntityService.retrieveAllByAuthorId(updatedAuthorEntity.getId(), false);

        //update edilen yazarın kitaplar listesindeki kitaplardaki yazar nesnesini de güncelliyoruz.
        bookEntityList.ifPresent(bookEntities ->
                bookEntities.forEach(book -> {
                            book.setAuthorEntity(updatedAuthorEntity);
                            bookEntityService.update(book);
                        }
                ));

        return updatedAuthorEntity.convertToDto();
    }

    @Override
    public AuthorDto updateAuthorName(String authorId, String authorName) {
        AuthorEntity authorEntity = authorEntityService.getById(authorId);

        return this.authorEntityService
                .updateName(authorEntity, authorName).convertToDto();
    }

    @Override
    public AuthorDto getAuthorByEmailAdress(String emailAddress) {

        return authorEntityService.getByEmailAdress(emailAddress).convertToDto();

    }


}
