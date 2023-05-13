package bookPublisherProject.data.request.bookRequests;

import bookPublisherProject.data.request.adminRequests.DeleteBookRequest;
import lombok.Builder;

@Builder
public record SoftDeleteBookRequest(
        String id
) {
    public DeleteBookRequest convertToDeleteRequest() {
        return DeleteBookRequest.builder()
                .id(id)
                .permanentlyDelete(false)
                .build();
    }
}
