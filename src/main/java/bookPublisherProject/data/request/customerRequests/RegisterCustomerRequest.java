package bookPublisherProject.data.request.customerRequests;

import bookPublisherProject.data.entity.users.CustomerEntity;
import bookPublisherProject.data.types.UserType;
import lombok.Builder;

@Builder
public record RegisterCustomerRequest(
        String name,
        String emailAddress,
        String password
) {
    public CustomerEntity convertToEntity() {
        return CustomerEntity.builder()
                .name(name)
                .emailAddress(emailAddress)
                .password(password)
                .userType(UserType.CUSTOMER)
                .build();
    }
}
