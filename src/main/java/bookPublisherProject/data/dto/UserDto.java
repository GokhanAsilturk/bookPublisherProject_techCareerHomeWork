package bookPublisherProject.data.dto;

import lombok.Builder;

@Builder
public record UserDto(
        String emailAddress
) {
}
