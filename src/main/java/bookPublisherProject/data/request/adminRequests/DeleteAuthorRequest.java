package bookPublisherProject.data.request.adminRequests;

import lombok.Builder;

@Builder
public record DeleteAuthorRequest(
        String id,
        boolean permanentlyDelete
) {
}
