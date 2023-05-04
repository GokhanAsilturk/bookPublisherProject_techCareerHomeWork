package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.entity.Author;
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

        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found!"));

        //Sistemden tamamen silinen yazarın bütün kitaplarını da kalıcı olarak siliyoruz.
        author.getBooks().forEach(book -> {
            this.bookService.permanentlyDeleteBook(book.getId());
        });

        this.authorRepository.deleteById(id);

        return author;
    }

    @Override
    public Author softDelete(int id) {
        //Sistemden silinen yazarın bütün kitaplarını da siliyoruz.
        this.authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found!"))
                .getBooks()
                .forEach(book -> {
                    this.bookService.softDeleteBook(book.getId());
                });

        this.authorRepository.findById(id).orElseThrow().setIsDeleted(true);
        return this.authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found!"));
    }

    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAllByIsDeletedFalse().orElseThrow(() -> new NotFoundException("There are no authors here."));
    }

    @Override
    public Author getById(int id) {
        return this.authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found!"));
    }
}
