package bookPublisherProject.repository;

import bookPublisherProject.data.entity.users.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByEmailAddress(String emailAddress);

    Optional<List<CustomerEntity>> findAllByIsDeletedFalse();

    Optional<CustomerEntity> findById(String id);
}
