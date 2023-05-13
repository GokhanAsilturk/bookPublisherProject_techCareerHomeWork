package bookPublisherProject.data.entity.users;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.baseEntitties.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("customers")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Customer extends User {


    public UserDto convertToUserDto() {
        return UserDto.builder()
                .emailAddress(getEmailAddress())
                .build();
    }
}
