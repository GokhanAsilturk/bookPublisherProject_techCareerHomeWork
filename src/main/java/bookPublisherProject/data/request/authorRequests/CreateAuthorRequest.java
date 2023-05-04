package bookPublisherProject.data.request.authorRequests;

import lombok.Builder;


@Builder
public record CreateAuthorRequest(
        String name,
        String emailAddress,
        String bio) {
}
