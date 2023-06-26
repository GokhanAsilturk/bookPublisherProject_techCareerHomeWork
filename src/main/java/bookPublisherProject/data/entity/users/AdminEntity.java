package bookPublisherProject.data.entity.users;

import bookPublisherProject.data.dto.AdminDto;
import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.baseEntitties.UserEntity;
import bookPublisherProject.data.types.UserType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("adminEntities")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AdminEntity extends UserEntity {

    @Builder.Default
    private final UserType userType = UserType.ADMIN;
    String name;
    String position;

    public UserDto convertToUserDto() {
        return UserDto.builder()
                .emailAddress(this.getEmailAddress())
                .userType(UserType.ADMIN)
                .build();
    }

    public AdminDto convertToDto() {
        return AdminDto.builder()
                .name(name)
                .position(position)
                .emailAddress(this.getEmailAddress())
                .build();
    }
}
