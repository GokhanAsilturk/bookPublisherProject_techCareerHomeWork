package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;
import bookPublisherProject.exception.DataNotFoundException;
import bookPublisherProject.exception.ExceptionType;
import bookPublisherProject.repository.AuthorRepository;
import bookPublisherProject.service.bookServices.BookEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorEntityService implements IAuthorEntityService {

    private final AuthorRepository authorRepository;

    private final BookEntityService bookEntityService;


    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {

        return this.authorRepository.save(authorEntity);
    }

    @Override
    public AuthorEntity register(AuthorEntity authorEntity) {

        return this.save(authorEntity);
    }

    @Override
    public void permanentlyDelete(AuthorEntity authorEntity) {

        this.authorRepository.delete(authorEntity);
    }

    @Override
    public void softDelete(AuthorEntity authorEntity) {
        authorEntity.setIsDeleted(true);
        this.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> getAll() {
        return this.authorRepository.findAllByIsDeletedFalse().orElseThrow(() ->
                new DataNotFoundException(ExceptionType.AUTHOR_LIST_NOT_FOUND, "Author List is Empty! :)"));
    }

    @Override
    public AuthorEntity getById(String id) {
        return this.authorRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException(ExceptionType.AUTHOR_DATA_NOT_FOUND, "AuthorEntity not found ! :)"));
    }

    @Override
    public AuthorEntity getByIdAndIsDeletedFalse(String id) {
        return this.authorRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() ->
                new DataNotFoundException(ExceptionType.AUTHOR_DATA_NOT_FOUND, "AuthorEntity not found ! :)"));
    }

    @Override
    public AuthorEntity getByName(String name) {
        return this.authorRepository.findByName(name).orElseThrow(()
                -> new DataNotFoundException(ExceptionType.AUTHOR_DATA_NOT_FOUND, "AuthorEntity not found ! :)"));
    }

    @Override
    public List<BookEntity> getBooksByName(String authorName) {

        AuthorEntity author = this.getByName(authorName);
        Optional<List<BookEntity>> bookEntityList =
                bookEntityService.retrieveAllByAuthorId(author.getId(), false);

        return bookEntityList.orElseGet(List::of);
    }

    @Override
    public AuthorEntity update(AuthorEntity authorEntity) {

        return this.save(authorEntity);
    }

    @Override
    public AuthorEntity updateName(AuthorEntity authorEntity, String name) {
        authorEntity.setName(name);
        return update(authorEntity);
    }

    @Override
    public AuthorEntity getByEmailAdress(String emailAddress) {
        return this.authorRepository.findByEmailAddress(emailAddress).orElseThrow(()
                -> new DataNotFoundException(ExceptionType.AUTHOR_DATA_NOT_FOUND, "AuthorEntity not found ! :)"));
    }


}
