package bookPublisherProject.repository;

import bookPublisherProject.data.entity.users.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,String> {
    Admin findByEmailAddress(String emailAddress);
}
