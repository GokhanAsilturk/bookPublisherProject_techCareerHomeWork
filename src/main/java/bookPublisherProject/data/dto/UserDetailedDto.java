package bookPublisherProject.data.dto;

import bookPublisherProject.data.types.UserType;
import lombok.Builder;

import java.util.Date;

@Builder
public record UserDetailedDto(
        UserType userType,
        String emailAddress,
        String id,
        Boolean isDeleted,
        Date createdDate,
        Date lastModified
) {

}
