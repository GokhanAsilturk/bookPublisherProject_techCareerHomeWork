package bookPublisherProject.repository;

import bookPublisherProject.data.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<List<Book>> findAllByIsDeletedFalse();
}
