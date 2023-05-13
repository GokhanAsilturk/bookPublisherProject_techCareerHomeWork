package bookPublisherProject.repository;

import bookPublisherProject.data.entity.users.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByEmailAddress(String emailAddress);
}
