package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.entity.Book;
import lombok.Builder;

@Builder
public record UpdateBookNameAndReleaseYearRequest(
        String bookId,
        String bookName,
        String releaseDate
) {
    public Book convertToEntity(Book book) {

        return Book.builder()
                .id(book.getId())
                .name(bookName)
                .description(book.getDescription())
                .releaseDate(releaseDate)
                .author(book.getAuthor())
                .build();
    }
}
