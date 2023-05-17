package bookPublisherProject.data.entity.users;

import bookPublisherProject.data.dto.CustomerDto;
import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.baseEntitties.User;
import bookPublisherProject.data.types.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("customers")
@AllArgsConstructor
@SuperBuilder
public class Customer extends User {

    String name;

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
