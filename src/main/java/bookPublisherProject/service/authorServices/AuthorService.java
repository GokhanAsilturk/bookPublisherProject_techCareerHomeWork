package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.authorRequests.DeleteAuthorRequest;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;
import bookPublisherProject.service.bookServices.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static bookPublisherProject.data.mapper.AuthorMapper.AUTHOR_MAPPER;
import static bookPublisherProject.data.mapper.BookMapper.BOOK_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

    private final AuthorEntityService authorEntityService;
    private final BookService bookService;

    @Override
    public AuthorDto createAuthor(CreateAuthorRequest createAuthorRequest) {

        return this.convertToDto(this.authorEntityService
                .save(AUTHOR_MAPPER.createAuthor(createAuthorRequest)));
    }

    @Override
    public AuthorDto deleteAuthor(DeleteAuthorRequest deleteAuthorRequest) {

        if (deleteAuthorRequest.permanentlyDelete()) {
            this.permanentlyDeleteAuthor(deleteAuthorRequest.id());
        }
        return this.softDeleteAuthor(deleteAuthorRequest.id());
    }

    @Override
    public void permanentlyDeleteAuthor(String id) {
        Author author = this.authorEntityService.getById(id);

        //Sistemden tamamen silinen yazarın bütün kitaplarını da kalıcı olarak siliyoruz.
        author.getBooks().forEach(book -> {
            this.bookService.permanentlyDeleteBook(book.getId());
        });
        this.authorEntityService.permanentlyDelete(id);
    }

    @Override
    public AuthorDto softDeleteAuthor(String id) {

        return this.convertToDto(this.authorEntityService.softDelete(id));
    }

    @Override
    public List<AuthorDto> getAllAuthors() {

        return this.authorEntityService.getAll().stream()
                .map(this::convertToDto).toList();
    }

    @Override
    public AuthorDto getAuthorById(String id) {
        return this.convertToDto(this.authorEntityService.getById(id));
    }

    @Override
    public AuthorDto getAuthorByName(String authorName) {
        return this.convertToDto(this.authorEntityService.getByName(authorName));
    }

    @Override
    public List<BookDto> getBooksByAuthorName(String authorName) {
        return this.authorEntityService.getBooksByName(authorName)
                .stream()
                .map(BOOK_MAPPER::convertToBookDto)
                .toList();
    }

    @Override
    public AuthorDto updateAuthor(UpdateAuthorRequest updateAuthorRequest) {

        return this.convertToDto(this.authorEntityService.update(
                updateAuthorRequest.authorId(),
                updateAuthorRequest.newAuthorName(),
                updateAuthorRequest.newEmailAddress(),
                updateAuthorRequest.newBio()
        ));
    }

    @Override
    public AuthorDto updateAuthorName(String authorId, String authorName) {
        return this.convertToDto(this.authorEntityService.updateName(authorId, authorName));
    }

    //Mapper kullanımı için kısayol oluşturduk.
    public AuthorDto convertToDto(Author author) {
        return AUTHOR_MAPPER.convertToAuthorDto(author);
    }

}
