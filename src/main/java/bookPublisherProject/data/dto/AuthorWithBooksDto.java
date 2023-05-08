package bookPublisherProject.data.dto;

import java.util.List;

public record AuthorWithBooksDto(
        String authorName,
        String emailAddress,
        String bio,
        List<BookDto> books
) {
}
