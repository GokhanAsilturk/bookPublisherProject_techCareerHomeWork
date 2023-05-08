package bookPublisherProject.data.request.bookRequests;

import lombok.Builder;

@Builder
public record UpdateBookNameAndReleaseYearRequest(
        String bookId,
        String bookName,
        String releaseYear
) {
}
