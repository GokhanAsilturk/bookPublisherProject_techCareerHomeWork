package bookPublisherProject.data.request.userRequests;

import bookPublisherProject.data.entity.baseEntitties.User;
import bookPublisherProject.data.types.UserType;
import lombok.Builder;

@Builder
public record LoginRequest(
        String emailAddress,
        String password
) {

    public User convertToEntity(UserType userType) {
        return User.builder()
                .emailAddress(emailAddress)
                .password(password)
                .userType(userType)
                .build();
    }

}
