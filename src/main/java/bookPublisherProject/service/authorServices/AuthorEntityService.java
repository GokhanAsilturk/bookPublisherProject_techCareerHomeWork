package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorEntityService implements IAuthorEntityService {

    private final AuthorRepository authorRepository;


    @Override
    public Author save(Author author) {
        return this.authorRepository.save(author);
    }

    @Override
    public void permanentlyDelete(String id) {

        this.authorRepository.deleteById(id);
    }

    @Override
    public Author softDelete(String id) {
        Author deletedAuthor = this.getById(id);

        //Sistemden silinen yazarın bütün kitaplarını da siliyoruz.
        deletedAuthor.getBooks().forEach(book -> book.setIsDeleted(true));


        deletedAuthor.setIsDeleted(true);
        this.authorRepository.save(deletedAuthor);
        return deletedAuthor;
    }

    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAllByIsDeletedFalse().orElseThrow(() ->
                new NotFoundException("There are no authors here."));
    }

    @Override
    public Author getById(String id) {
        return this.authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found!"));
    }

    @Override
    public Author getByName(String name) {
        return this.authorRepository.findByName(name).orElseThrow(() -> new NotFoundException("Author not found"));
    }

    @Override
    public List<Book> getBooksByName(String authorName) {
        return this.getByName(authorName).getBooks();
    }

    @Override
    public Author update(Author author) {
        return this.save(author);
    }

    @Override
    public Author updateName(Author author, String name) {
        author.setName(name);
        return update(author);
    }

    public Author addBookInBookListAndUpdate(Author author, Book book) {

        List<Book> bookList = author.getBooks();
        bookList.add(book);
        author.setBooks(bookList);
        return this.update(author);
    }

    public Author removeBookInBookListAndUpdate(Author author, Book book) {
        List<Book> bookList = author.getBooks();
        bookList.remove(book);
        author.setBooks(bookList);
        return this.update(author);
    }
}
