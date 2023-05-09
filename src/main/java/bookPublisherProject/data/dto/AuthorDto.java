package bookPublisherProject.data.dto;

import lombok.Builder;


@Builder
public record AuthorDto(
        String name,
        String emailAddress,
        String bio) {

}
