package bookPublisherProject.data.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AuthorWithBooksDto(
        String name,
        String emailAddress,
        String bio,
        List<BookDto> books
) {
}
