package bookPublisherProject.data.request.bookRequests;

import lombok.Builder;

@Builder
public record UpdateBookRequest(
        int bookId,
        String newBookName,
        String newDescription,
        String newReleaseDate
) {

}
