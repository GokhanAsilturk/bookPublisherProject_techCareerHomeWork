package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.exception.DataNotFoundException;
import bookPublisherProject.exception.ExceptionType;
import bookPublisherProject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookEntityService implements IBookEntityService {

    private final BookRepository bookRepository;


    @Override
    public BookEntity create(BookEntity bookEntity) {

        return this.bookRepository.save(bookEntity);
    }

    @Override
    public void softDelete(BookEntity bookEntity) {
        //siliyoruz
        bookEntity.setIsDeleted(true);
        this.save(bookEntity);
    }

    @Override
    public void permanentlyDelete(BookEntity bookEntity) {
        this.bookRepository.delete(bookEntity);
    }

    @Override
    public List<BookEntity> getAll() {

        return this.bookRepository.findAllByIsDeletedFalse().orElseThrow(() ->
                new DataNotFoundException(ExceptionType.BOOK_LIST_NOT_FOUND, "There is no bookEntities here! :) "));


    }

    @Override
    public BookEntity getById(String id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException(ExceptionType.BOOK_DATA_NOT_FOUND, "BookEntity not found! :)"));
    }

    @Override
    public BookEntity update(BookEntity bookEntity) {
        return this.save(bookEntity);
    }

    @Override
    public BookEntity publish(BookEntity bookEntity) {
        return this.save(bookEntity);
    }


    public BookEntity save(BookEntity bookEntity) {
        return this.bookRepository.save(bookEntity);
    }

    public Optional<List<BookEntity>> retrieveAllByAuthorId(String authorId, boolean allData) {

        Optional<List<BookEntity>> bookEntityList = this.bookRepository.findAllByAuthorEntityId(authorId);

        if (!allData) {
            // AllData izni yok ise soft silinen kitapları listeden çıkartmak için
            //isdeleted = false olan bütün kitapları çağırıyoruz.
            bookEntityList = bookRepository.findAllByIsDeletedFalseAndAuthorEntityId(authorId);
        }

        return bookEntityList;
    }
}
