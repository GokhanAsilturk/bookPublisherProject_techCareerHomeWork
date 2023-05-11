package bookPublisherProject.data.request.authorRequests;

import lombok.Builder;

@Builder
public record SoftDeleteAuthorRequest(
        String id
) {
    public DeleteAuthorRequest convertToDeleteRequest(){
       return DeleteAuthorRequest.builder()
                .id(id)
                .permanentlyDelete(false)
                .build();
    }
}
