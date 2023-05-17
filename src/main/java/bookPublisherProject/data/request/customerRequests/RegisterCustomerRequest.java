package bookPublisherProject.data.request.customerRequests;

import bookPublisherProject.data.entity.users.Customer;
import bookPublisherProject.data.types.UserType;
import lombok.Builder;

@Builder
public record RegisterCustomerRequest(
        String name,
        String emailAddress,
        String password
) {
    public Customer convertToEntity() {
        return Customer.builder()
                .name(name)
                .emailAddress(emailAddress)
                .password(password)
                .userType(UserType.CUSTOMER)
                .build();
    }
}
