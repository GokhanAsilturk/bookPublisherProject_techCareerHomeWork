package bookPublisherProject.data.request.adminRequests;

import bookPublisherProject.data.entity.users.Admin;
import bookPublisherProject.data.types.UserType;
import lombok.Builder;

@Builder
public record CreateAdminRequest(
        String name,
        String emailAddress,
        String password,

        String position
) {

    public Admin convertToEntity(){
        return Admin.builder()
                .name(name)
                .emailAddress(emailAddress)
                .password(password)
                .position(position)
                .userType(UserType.ADMIN)
                .build();
    }
}
