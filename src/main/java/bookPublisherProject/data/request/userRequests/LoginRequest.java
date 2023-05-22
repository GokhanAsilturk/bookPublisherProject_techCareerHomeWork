package bookPublisherProject.data.request.userRequests;

import bookPublisherProject.data.entity.baseEntitties.UserEntity;
import bookPublisherProject.data.types.UserType;
import lombok.Builder;

@Builder
public record LoginRequest(
        String emailAddress,
        String password
) {

    public UserEntity convertToEntity(UserType userType) {
        return UserEntity.builder()
                .emailAddress(emailAddress)
                .password(password)
                .userType(userType)
                .build();
    }

}
