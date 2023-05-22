package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;
import lombok.Builder;

@Builder
public record CreateBookRequest (
        String authorId,
        String name,
        String description,
        String releaseDate
){

    public BookEntity convertToEntity(AuthorEntity authorEntity){
        return BookEntity.builder()
                .name(name)
                .description(description)
                .releaseDate(releaseDate)
                .authorEntity(authorEntity)
                .build();
    }
}
