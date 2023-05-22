package bookPublisherProject.data.entity.baseEntitties;

import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.types.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("users")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity{

    @Id
    private String id;
    @NotNull
    private String emailAddress;
    private String password;
    private UserType userType;


    public UserDto convertToUserDto() {
        return UserDto.builder()
                .emailAddress(emailAddress)
                .userType(userType)
                .build();
    }

}
