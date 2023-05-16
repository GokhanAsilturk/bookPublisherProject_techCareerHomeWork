package bookPublisherProject.repository;

import bookPublisherProject.data.entity.baseEntitties.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String > {
    Optional<Object> findByEmailAddress(String emailAddress);
}
