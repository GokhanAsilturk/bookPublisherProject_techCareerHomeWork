package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.entity.users.AuthorEntity;
import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;
import lombok.Builder;

@Builder
public record UpdateBookAndAuthorRequest(
        UpdateBookRequest updateBookRequest,
        UpdateAuthorRequest updateAuthorRequest
) {
    public BookEntity convertToBookEntity(BookEntity bookEntity) {
        BookEntity updatedBookEntity = updateBookRequest.convertToEntity(bookEntity);

        updatedBookEntity.setAuthorEntity(convertToAuthorEntity());
        return updatedBookEntity;
    }

    public AuthorEntity convertToAuthorEntity() {

        return updateAuthorRequest.convertToEntity();
    }
}
