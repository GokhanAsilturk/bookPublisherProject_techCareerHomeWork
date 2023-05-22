package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.entity.BookEntity;
import lombok.Builder;

@Builder
public record UpdateBookNameAndReleaseYearRequest(
        String bookId,
        String bookName,
        String releaseDate
) {
    public BookEntity convertToEntity(BookEntity bookEntity) {

        return BookEntity.builder()
                .id(bookEntity.getId())
                .name(bookName)
                .description(bookEntity.getDescription())
                .releaseDate(releaseDate)
                .authorEntity(bookEntity.getAuthorEntity())
                .build();
    }
}
