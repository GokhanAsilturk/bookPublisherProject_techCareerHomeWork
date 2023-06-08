package bookPublisherProject.data.request.authorRequests;

import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;

public record PublishNewBookRequest(

        String name,
        String description,
        String releaseDate,
        String authorId
) {
    public BookEntity convertToEntity(AuthorEntity authorEntity) {
        return BookEntity.builder()
                .name(name)
                .description(description)
                .releaseDate(releaseDate)
                .authorEntity(authorEntity)
                .build();
    }
}
