package bookPublisherProject.data.dto;

import bookPublisherProject.data.types.UserType;
import lombok.Builder;

@Builder
public record UserDto(
        String name,
        String emailAddress,
        UserType userType
) {
}
