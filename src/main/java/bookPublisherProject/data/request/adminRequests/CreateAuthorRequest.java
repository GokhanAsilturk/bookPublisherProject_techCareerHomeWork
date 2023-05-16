package bookPublisherProject.data.request.adminRequests;

import bookPublisherProject.data.entity.users.Author;
import bookPublisherProject.data.types.UserType;
import lombok.Builder;


@Builder
public record CreateAuthorRequest(
        String name,
        String emailAddress,

        String password,

        String bio) {

    public Author convertToEntity(){
        return Author.builder()
                .name(name)
                .emailAddress(emailAddress)
                .password(password)
                .bio(bio)
                .userType(UserType.AUTHOR)
                .build();
    }
}