package bookPublisherProject.repository;

import bookPublisherProject.data.entity.users.AdminEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<AdminEntity, String> {
    AdminEntity findByEmailAddress(String emailAddress);
}
