package bookPublisherProject.repository;

import bookPublisherProject.data.entity.users.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
    CustomerEntity findByEmailAddress(String emailAddress);
}
