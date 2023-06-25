package bookPublisherProject.repository;

import bookPublisherProject.data.entity.users.AuthorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<AuthorEntity, String> {

    Optional<List<AuthorEntity>> findAllByIsDeletedFalse();

    Optional<AuthorEntity> findByIdAndIsDeletedFalse(String id);

    Optional<AuthorEntity> findByName(String name);

    Optional<AuthorEntity> findByEmailAddress(String emailAddress);
}
