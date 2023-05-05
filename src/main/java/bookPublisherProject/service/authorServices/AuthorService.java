package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.mapper.BookMapper;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.authorRequests.DeleteAuthorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static bookPublisherProject.data.mapper.AuthorMapper.AUTHOR_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

    private final AuthorEntityService authorEntityService;
    private final BookMapper bookMapper;

    @Override
    public AuthorDto createAuthor(CreateAuthorRequest createAuthorRequest) {

        return this.convertToDto(this.authorEntityService
                .save(AUTHOR_MAPPER.createAuthor(createAuthorRequest)));
    }

    @Override
    public AuthorDto deleteAuthor(DeleteAuthorRequest deleteAuthorRequest) {

        if (deleteAuthorRequest.permanentlyDelete()) {
            return this.permanentlyDeleteAuthor(deleteAuthorRequest.id());
        }
        return this.softDeleteAuthor(deleteAuthorRequest.id());
    }

    @Override
    public AuthorDto permanentlyDeleteAuthor(int id) {
        return this.convertToDto(this.authorEntityService.permanentlyDelete(id));
    }

    @Override
    public AuthorDto softDeleteAuthor(int id) {

        return this.convertToDto(this.authorEntityService.softDelete(id));
    }

    @Override
    public List<AuthorDto> getAllAuthors() {

        return this.authorEntityService.getAll().stream()
                .map(this::convertToDto).toList();
    }

    @Override
    public AuthorDto getAuthorById(int id) {
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
                .map(bookMapper::convertToBookDto)
                .toList();
    }

    @Override
    public AuthorDto updateAuthorName(int authorId, String authorName) {
        return this.convertToDto(this.authorEntityService.updateName(authorId, authorName));
    }

    //Mapper kullanımı için kısayol oluşturduk.
    public AuthorDto convertToDto(Author author) {
        return AUTHOR_MAPPER.convertToAuthorDto(author);
    }

}
