package bookPublisherProject.repository;

import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<BookEntity, String> {

    Optional<List<BookEntity>> findAllByIsDeletedFalse();

    Optional<List<BookEntity>> findAllByAuthorEntityId(String authorEntityId);
}
