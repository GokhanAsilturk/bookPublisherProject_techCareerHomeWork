package bookPublisherProject.data.entity.users;

import bookPublisherProject.data.dto.CustomerDto;
import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.baseEntitties.UserEntity;
import bookPublisherProject.data.types.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("customerEntities")
@AllArgsConstructor
@SuperBuilder
public class CustomerEntity extends UserEntity {
    @Builder.Default
    private final UserType userType = UserType.CUSTOMER;
    private String name;

    public UserDto convertToUserDto() {
        return UserDto.builder()
                .emailAddress(getEmailAddress())
                .userType(UserType.CUSTOMER)
                .build();
    }

    public CustomerDto convertToDto() {
        return CustomerDto.builder()
                .name(name)
                .emailAddress(getEmailAddress())
                .build();
    }
}
