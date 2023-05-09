package bookPublisherProject.data.dto;

import lombok.Builder;


@Builder
public record BookDto(
        String name,
        String description,
        String releaseDate,
        AuthorDto authorDto) {

}
