package bookPublisherProject.data.dto;

import lombok.Builder;

@Builder
public record AdminDto(
        String name,
        String emailAddress,
        String position
) {
}
