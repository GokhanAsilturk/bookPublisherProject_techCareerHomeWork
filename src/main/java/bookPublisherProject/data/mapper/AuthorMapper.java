package bookPublisherProject.data.mapper;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationName = "AuthorMapperImpl", componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper AUTHOR_MAPPER = Mappers.getMapper(AuthorMapper.class);

    AuthorDto convertToAuthorDto(Author author);

    Author createAuthor(CreateAuthorRequest createAuthorRequest);

    Author updateAuthor(UpdateAuthorRequest updateAuthorRequest);
}
