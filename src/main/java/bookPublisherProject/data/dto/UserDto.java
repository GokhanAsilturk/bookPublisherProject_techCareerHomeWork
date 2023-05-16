package bookPublisherProject.data.dto;

import bookPublisherProject.data.types.UserType;
import lombok.Builder;

@Builder
public record UserDto(
        String emailAddress,
        UserType userType
) {
}
