package bookPublisherProject.data.request.bookRequests;

import lombok.Builder;

@Builder
public record DeleteBookRequest(
        String id,
        boolean permanentlyDelete
) {

}
