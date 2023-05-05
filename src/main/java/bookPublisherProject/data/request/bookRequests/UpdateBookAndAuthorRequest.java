package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.request.authorRequests.UpdateAuthorRequest;
import lombok.Builder;

@Builder
public record UpdateBookAndAuthorRequest(
        UpdateBookRequest updateBookRequest,
        UpdateAuthorRequest updateAuthorRequest
) {
}
