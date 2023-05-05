package bookPublisherProject.data.request.bookRequests;

import lombok.Builder;

@Builder
public record UpdateBookNameAndReleaseYearRequest(
        String name,
        String releaseYear
) {
}
