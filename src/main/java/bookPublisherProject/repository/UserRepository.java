package bookPublisherProject.repository;

import bookPublisherProject.data.entity.baseEntitties.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<Object> findByEmailAddress(String emailAddress);

    Optional<List<UserEntity>> findAllByIsDeletedFalse();
}
