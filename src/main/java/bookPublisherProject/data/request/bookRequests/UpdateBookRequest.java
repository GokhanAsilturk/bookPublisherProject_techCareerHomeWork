package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.entity.Author;
import bookPublisherProject.data.entity.Book;
import lombok.Builder;

@Builder
public record UpdateBookRequest(
        String bookId,
        String newBookName,
        String newDescription,
        String newReleaseDate
) {

    public Book convertToEntity(Book book) {
        return Book.builder()
                .id(book.getId())
                .name(newBookName)
                .description(newDescription)
                .releaseDate(newReleaseDate)
                .author(book.getAuthor())
                .build();
    }

}
