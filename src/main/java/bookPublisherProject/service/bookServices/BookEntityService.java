package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.entity.Book;
import bookPublisherProject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookEntityService implements IBookEntityService {

    private final BookRepository bookRepository;


    @Override
    public Book create(Book book) {

        return this.bookRepository.save(book);
    }

    @Override
    public void softDelete(Book book) {
        //siliyoruz
        book.setIsDeleted(true);
        this.save(book);
    }

    @Override
    public void permanentlyDelete(Book book) {
        this.bookRepository.delete(book);
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAllByIsDeletedFalse().orElseThrow(() ->
                new NotFoundException("There is no books here."));
    }

    @Override
    public Book getById(String id) {
        return this.bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Book not found!"));
    }

    @Override
    public Book update(Book book) {
        return this.save(book);
    }

    @Override
    public Book publish(Book book) {
        return this.save(book);
    }

    public Book save(Book book) {
        return this.bookRepository.save(book);
    }
}
