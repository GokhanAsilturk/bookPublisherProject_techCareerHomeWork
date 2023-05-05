package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.repository.AuthorRepository;
import bookPublisherProject.service.bookServices.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorEntityService implements IAuthorEntityService {

    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @Override
    public Author save(Author author) {

        return this.authorRepository.save(author);
    }

    @Override
    public Author permanentlyDelete(int id) {

        Author author = this.getById(id);

        //Sistemden tamamen silinen yazarın bütün kitaplarını da kalıcı olarak siliyoruz.
        author.getBooks().forEach(book -> {
            this.bookService.permanentlyDeleteBook(book.getId());
        });

        this.authorRepository.deleteById(id);

        return author;
    }

    @Override
    public Author softDelete(int id) {
        Author deletedAuthor = this.getById(id);
        //Sistemden silinen yazarın bütün kitaplarını da siliyoruz.
        deletedAuthor.getBooks().forEach(book -> this.bookService.softDeleteBook(book.getId()));

        this.getById(id).setIsDeleted(true);
        return deletedAuthor;
    }

    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAllByIsDeletedFalse().orElseThrow(() -> new NotFoundException("There are no authors here."));
    }

    @Override
    public Author getById(int id) {
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
    public Author updateName(int id, String name) {
        this.getById(id).setName(name);
        return this.getById(id);
    }
}
