package bookPublisherProject.data.dto;

import lombok.Builder;


@Builder
public record BookDto(
        String bookName,
        String description,
        String releaseDate,
        AuthorDto authorDto) {

}
