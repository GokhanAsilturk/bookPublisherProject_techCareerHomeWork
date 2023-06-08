package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.entity.BookEntity;
import lombok.Builder;

@Builder
public record UpdateBookNameAndReleaseYearRequest(
        String bookId,
        String newBookName,
        String newReleaseYear
) {
    public BookEntity convertToEntity(BookEntity bookEntity) {

        return BookEntity.builder()
                .id(bookEntity.getId())
                .name(newBookName)
                .description(bookEntity.getDescription())
                .releaseDate(newReleaseYear)
                .authorEntity(bookEntity.getAuthorEntity())
                .build();
    }
}
