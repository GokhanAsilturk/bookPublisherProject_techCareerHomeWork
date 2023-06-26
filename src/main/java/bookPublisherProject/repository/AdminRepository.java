package bookPublisherProject.repository;

import bookPublisherProject.data.entity.users.AdminEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends MongoRepository<AdminEntity, String> {
    Optional<AdminEntity> findByEmailAddress(String emailAddress);

    Optional<List<AdminEntity>> findAllByIsDeletedFalse();
}
