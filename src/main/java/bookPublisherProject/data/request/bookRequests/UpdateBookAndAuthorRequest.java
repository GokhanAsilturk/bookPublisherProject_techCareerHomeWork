package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.entity.users.Author;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;
import lombok.Builder;

@Builder
public record UpdateBookAndAuthorRequest(
        UpdateBookRequest updateBookRequest,
        UpdateAuthorRequest updateAuthorRequest
) {
    public Book convertToBookEntity(Book book) {
        Book updatedBook = updateBookRequest.convertToEntity(book);
        updatedBook.setAuthor(convertToAuthorEntity());
        return updatedBook;
    }

    public Author convertToAuthorEntity() {

        return updateAuthorRequest.convertToEntity();
    }
}
