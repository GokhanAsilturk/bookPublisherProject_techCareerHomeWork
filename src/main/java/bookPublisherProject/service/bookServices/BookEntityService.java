package bookPublisherProject.service.bookServices;

import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.PublishNewBookRequest;
import bookPublisherProject.data.request.bookRequests.UpdateBookRequest;
import bookPublisherProject.repository.BookRepository;
import bookPublisherProject.service.authorServices.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookEntityService implements IBookEntityService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;


    @Override
    public Book save(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Book softDelete(int id) {

        //Nesneye kopya alıyoruz
        Book deletedBook = this.getById(id);
        //siliyoruz
        this.getById(id).setIsDeleted(true);

        //silme işleminden sonra silinen kitabın yazarının başka bir kitabı kalmıyorsa, yazar da sistemden silinir.
        //kitapları gez ve yazarlarına bak. eğer silinen kitabın yazarı ile eşleşen bir yazar yok ise yazarı sil.
        if (!this.getAll()
                .stream()
                .map(Book::getAuthor)
                .toList()
                .contains(deletedBook.getAuthor())) {
            this.authorService.softDeleteAuthor(deletedBook.getId());
        }

        return deletedBook;
    }

    @Override
    public Book permanentlyDelete(int id) {
        //Nesneye kopya alıyoruz
        Book deletedBook = this.getById(id);
        //siliyoruz
        this.getById(id).setIsDeleted(true);

        //silme işleminden sonra silinen kitabın yazarının başka bir kitabı kalmıyorsa, yazar da sistemden tamamen silinir.
        //kitapları gez ve yazarlarına bak. eğer silinen kitabın yazarı ile eşleşen bir yazar yok ise yazarı tamamen sil.
        if (!this.getAll()
                .stream()
                .map(Book::getAuthor)
                .toList()
                .contains(deletedBook.getAuthor())) {
            this.authorService.permanentlyDeleteAuthor(deletedBook.getAuthor().getId());
        }
        return deletedBook;
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAllByIsDeletedFalse().orElseThrow(() -> new NotFoundException("There is no books here."));
    }

    @Override
    public Book getById(int id) {
        return this.bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found!"));
    }

    @Override
    public List<Book> getByAuthorName(String authorName) {
        //
        List<Book> booksOfAuthor = new ArrayList<>();
        this.getAll()
                .forEach(book -> {

                    if (book.getAuthor().getName().equals(authorName)) {

                        book.getAuthor()
                                .getBooks()
                                .stream()
                                .map(booksOfAuthor::add);
                    }
                });
        if (!booksOfAuthor.isEmpty()) {
            return booksOfAuthor;
        }
        return List.of();

    }

    @Override
    public Book update(int id, String newBookName, String newDescription, String newReleaseDate) {
        this.getById(id).setName(newBookName);
        this.getById(id).setDescription(newDescription);
        this.getById(id).setReleaseDate(newReleaseDate);
        return null;
    }

    @Override
    public Book updateBookAndAuthor(int bookId, Book newBook, Author newAuthor) {
        //Kitabı güncelliyoruz.
        Book book = this.getById(bookId);
        book.setName(newBook.getName());
        book.setDescription(newBook.getDescription());
        book.setReleaseDate(newBook.getReleaseDate());

        //Yazarı güncelliyoruz.
        Author author = book.getAuthor();
        author.setName(newAuthor.getName());
        author.setEmailAddress(newAuthor.getEmailAddress());
        author.setBio(newAuthor.getBio());

        //kitaba yeni yazarı kaydediyoruz.
        book.setAuthor(author);

        //kitabı güncellenmiş hali ile kaydediyoruz.
        return this.save(book);
    }

    @Override
    public Book updateNameOfAuthorByBook(int bookId, String authorName) {
        this.authorService.updateAuthorName(this.getById(bookId).getAuthor().getId(), authorName);
        this.getById(bookId).setAuthor(this.getById(bookId).getAuthor());
        return this.getById(bookId);
    }

    @Override
    public Book updateBookNameAndReleaseYear(String bookName, String releaseYear) {

        return null;
    }

    @Override
    public Book publish(PublishNewBookRequest publishNewBookRequest) {
        return null;
    }
}
