package bookPublisherProject.data.request.authorRequests;

import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;

public record PublishNewBookRequest(

        String name,
        String description,
        String releaseDate,
        String authorId
) {
    public Book convertToEntity(Author author){
        return Book.builder()
                .name(name)
                .description(description)
                .releaseDate(releaseDate)
                .author(author)
                .build();
    }
}
