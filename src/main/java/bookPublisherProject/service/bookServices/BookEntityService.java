package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;
import bookPublisherProject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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
                new NotFoundException("There is no bookEntities here."));
    }

    @Override
    public BookEntity getById(String id) {
        return this.bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("BookEntity not found!"));
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
            // isDelete attribute u true ise kitabı listeden çıkartıyoruz.
            bookEntityList.ifPresent(bookEntities ->
                    bookEntities.forEach(bookEntity -> {
                        if (bookEntity.getIsDeleted()) {
                            bookEntities.remove(bookEntity);
                        }
                    }));
        }

        return bookEntityList;
    }
}
