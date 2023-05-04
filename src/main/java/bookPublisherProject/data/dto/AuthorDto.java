package bookPublisherProject.data.dto;

import lombok.Builder;


@Builder
public record AuthorDto(
        String authorName,
        String emailAddress,
        String bio) {

}
