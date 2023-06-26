package bookPublisherProject.data.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record AdminDetailedDto(
        String position,
        String emailAddress,
        String id,
        Boolean isDeleted,
        Date createdDate,
        Date lastModified
) {
}
