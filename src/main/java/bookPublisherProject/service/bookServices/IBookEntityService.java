package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface IBookEntityService {

    BookEntity create(BookEntity bookEntity);

    void softDelete(BookEntity bookEntity);

    void permanentlyDelete(BookEntity bookEntity);

    List<BookEntity> getAll();

    BookEntity getById(String id);

    BookEntity update(BookEntity bookEntity);

    BookEntity publish(BookEntity bookEntity);

    Optional<List<BookEntity>> retrieveAllByAuthorId(String authorId, boolean allData);
}
