package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.entity.BookEntity;
import lombok.Builder;

@Builder
public record UpdateBookRequest(
        String bookId,
        String newBookName,
        String newDescription,
        String newReleaseDate
) {

    public BookEntity convertToEntity(BookEntity bookEntity) {
        return BookEntity.builder()
                .id(bookEntity.getId())
                .name(newBookName)
                .description(newDescription)
                .releaseDate(newReleaseDate)
                .authorEntity(bookEntity.getAuthorEntity())
                .build();
    }

}
