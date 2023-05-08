package bookPublisherProject.data.request.authorRequests;

import lombok.Builder;

@Builder
public record DeleteAuthorRequest(
        String id,
        boolean permanentlyDelete
) {
}
