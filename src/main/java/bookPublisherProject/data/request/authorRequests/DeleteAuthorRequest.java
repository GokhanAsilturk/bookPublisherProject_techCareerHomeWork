package bookPublisherProject.data.request.authorRequests;

import lombok.Builder;

@Builder
public record DeleteAuthorRequest(
        int id,
        boolean permanentlyDelete
) {
}
