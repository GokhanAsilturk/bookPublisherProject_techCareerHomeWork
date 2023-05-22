package bookPublisherProject.data.entity.users;

import bookPublisherProject.data.dto.AdminDto;
import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.baseEntitties.UserEntity;
import bookPublisherProject.data.types.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("admins")
@AllArgsConstructor
@SuperBuilder
public class AdminEntity extends UserEntity {

    String name;
    String position;


    public UserDto convertToUserDto() {
        return UserDto.builder()
                .emailAddress(getEmailAddress())
                .userType(UserType.ADMIN)
                .build();
    }

    public AdminDto convertToDto() {
       return AdminDto.builder()
                .name(name)
                .position(position)
                .emailAddress(getEmailAddress())
                .build();
    }
}
