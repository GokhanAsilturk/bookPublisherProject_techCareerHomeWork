package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;

import java.util.List;

public interface IAuthorEntityService {

    AuthorEntity save(AuthorEntity authorEntity);

    AuthorEntity register(AuthorEntity authorEntity);

    void permanentlyDelete(AuthorEntity authorEntity);

    void softDelete(AuthorEntity authorEntity);

    List<AuthorEntity> getAll();

    AuthorEntity getById(String id);

    AuthorEntity getByIdAndIsDeletedFalse(String id);

    AuthorEntity getByName(String name);

    List<BookEntity> getBooksByName(String name);

    AuthorEntity update(AuthorEntity authorEntity);

    AuthorEntity updateName(AuthorEntity authorEntity, String name);

    AuthorEntity getByEmailAdress(String emailAddress);
}
