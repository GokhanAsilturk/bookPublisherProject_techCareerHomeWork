package bookPublisherProject.data.request.authorRequests;

import bookPublisherProject.data.entity.Author;
import lombok.Builder;


@Builder
public record CreateAuthorRequest(
        String name,
        String emailAddress,
        String bio) {

    public Author convertToEntity(){
        return Author.builder()
                .name(name)
                .emailAddress(emailAddress)
                .bio(bio)
                .build();
    }
}
