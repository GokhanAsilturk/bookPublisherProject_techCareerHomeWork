package bookPublisherProject.data.request.bookRequests;

import lombok.Builder;

@Builder
public record UpdateBookRequest(
        String bookId,
        String newBookName,
        String newDescription,
        String newReleaseDate
) {

}
