package bookPublisherProject.data.dto;

import lombok.Builder;

@Builder
public record CustomerDto(
        String name,
        String emailAddress
) {
}
