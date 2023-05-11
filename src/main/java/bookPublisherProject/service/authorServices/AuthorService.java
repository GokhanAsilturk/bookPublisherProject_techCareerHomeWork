package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.authorRequests.DeleteAuthorRequest;
import bookPublisherProject.data.request.authorRequests.RegisterAuthorRequest;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;
import bookPublisherProject.service.bookServices.BookEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

    private final AuthorEntityService authorEntityService;
    private final BookEntityService bookEntityService;

    @Override
    public AuthorDto createAuthor(CreateAuthorRequest createAuthorRequest) {

        return this.convertToDto(this.authorEntityService
                .save(createAuthorRequest.convertToEntity()));
    }

    @Override
    public AuthorDto registerAuthor(RegisterAuthorRequest registerAuthorRequest) {
        return this.convertToDto(authorEntityService.register(
                registerAuthorRequest.convertToCreateAuthorRequest().convertToEntity()
        ));
    }

    @Override
    public void deleteAuthor(DeleteAuthorRequest deleteAuthorRequest) {

        if (deleteAuthorRequest.permanentlyDelete()) {
            this.permanentlyDeleteAuthor(deleteAuthorRequest.id());
        }
        this.softDeleteAuthor(deleteAuthorRequest.id());
    }

    @Override
    public void permanentlyDeleteAuthor(String id) {

        Author author = this.authorEntityService.getById(id);

        //Sistemden tamamen silinecek yazarın bütün kitaplarını da kalıcı olarak siliyoruz.
        author.getBooks().forEach(this.bookEntityService::permanentlyDelete);

        this.authorEntityService.permanentlyDelete(author);
    }

    @Override
    public void softDeleteAuthor(String id) {
        Author author = this.authorEntityService.getById(id);

        //Sistemden soft silinecek yazarın bütün kitaplarını da soft siliyoruz.
        author.getBooks().forEach(bookEntityService::softDelete);

        this.authorEntityService.softDelete(author);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return this.authorEntityService.getAll().stream()
                .map(this::convertToDto)
                .toList();
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
                .map(Book::convertToDto)
                .toList();
    }

    @Override
    public AuthorDto updateAuthor(UpdateAuthorRequest updateAuthorRequest) {

        return this.convertToDto(this.authorEntityService
                .update(updateAuthorRequest.convertToEntity()));
    }

    @Override
    public AuthorDto updateAuthorName(String authorId, String authorName) {
        return this.convertToDto(this.authorEntityService
                .updateName(authorEntityService.getById(authorId), authorName));
    }




    public AuthorDto convertToDto(Author author) {

        return author.convertToDto();
    }

}
