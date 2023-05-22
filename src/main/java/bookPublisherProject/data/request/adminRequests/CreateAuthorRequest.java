package bookPublisherProject.data.request.adminRequests;

import bookPublisherProject.data.entity.users.AuthorEntity;
import bookPublisherProject.data.types.UserType;
import lombok.Builder;


@Builder
public record CreateAuthorRequest(
        String name,
        String emailAddress,

        String password,

        String bio) {

    public AuthorEntity convertToEntity() {
        return AuthorEntity.builder()
                .name(name)
                .emailAddress(emailAddress)
                .password(password)
                .bio(bio)
                .userType(UserType.AUTHOR)
                .build();
    }
}
