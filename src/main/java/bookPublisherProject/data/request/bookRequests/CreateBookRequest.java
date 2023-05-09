package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import lombok.Builder;

@Builder
public record CreateBookRequest (
        String authorId,
        String name,
        String description,
        String releaseDate
){

    public Book convertToEntity(Author author){
        return Book.builder()
                .author(author)
                .name(name)
                .description(description)
                .releaseDate(releaseDate)
                .build();
    }
}
