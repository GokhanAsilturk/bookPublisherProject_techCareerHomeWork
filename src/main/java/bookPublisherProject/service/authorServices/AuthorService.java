package bookPublisherProject.service.authorServices;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static bookPublisherProject.data.mapper.AuthorMapper.AUTHOR_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

    private final AuthorEntityService authorEntityService;

    @Override
    public AuthorDto createAuthor(CreateAuthorRequest createAuthorRequest) {

        return AUTHOR_MAPPER.convertToAuthorDto(this.authorEntityService
                .save(AUTHOR_MAPPER.createAuthor(createAuthorRequest)));
    }

    @Override
    public AuthorDto permanentlyDeleteAuthor(int id) {
        return AUTHOR_MAPPER.convertToAuthorDto(this.authorEntityService.permanentlyDelete(id));
    }

    @Override
    public AuthorDto softDeleteAuthor(int id) {

        return AUTHOR_MAPPER.convertToAuthorDto(this.authorEntityService.softDelete(id));
    }

    @Override
    public List<AuthorDto> getAllAuthors() {

        return this.authorEntityService.getAll().stream()
                .map(AUTHOR_MAPPER::convertToAuthorDto).collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(int id) {
        return AUTHOR_MAPPER.convertToAuthorDto(this.authorEntityService.getById(id));
    }
}
