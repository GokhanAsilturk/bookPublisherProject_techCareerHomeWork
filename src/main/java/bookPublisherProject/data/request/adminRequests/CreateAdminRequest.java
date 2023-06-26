package bookPublisherProject.data.request.adminRequests;

import bookPublisherProject.data.entity.users.AdminEntity;
import lombok.Builder;

@Builder
public record CreateAdminRequest(
        String name,
        String emailAddress,
        String password,

        String position
) {

    public AdminEntity convertToEntity() {
        return AdminEntity.builder()
                .name(name)
                .emailAddress(emailAddress)
                .password(password)
                .position(position)
                .build();
    }
}
