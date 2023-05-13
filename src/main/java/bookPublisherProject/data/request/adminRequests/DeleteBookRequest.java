package bookPublisherProject.data.request.adminRequests;

import lombok.Builder;

@Builder
public record DeleteBookRequest(
        String id,
        boolean permanentlyDelete
) {

}
