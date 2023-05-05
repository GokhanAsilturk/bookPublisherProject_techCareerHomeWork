package bookPublisherProject.data.request.bookRequests;

import lombok.Builder;

@Builder
public record DeleteBookRequest(
        int id,
        boolean permanentlyDelete
) {

}
