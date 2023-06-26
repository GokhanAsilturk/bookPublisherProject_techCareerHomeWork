package bookPublisherProject.data.dto;

import lombok.Builder;

@Builder
public record ItemDetailedDto<T>(
        T optionalItem
) {
}
