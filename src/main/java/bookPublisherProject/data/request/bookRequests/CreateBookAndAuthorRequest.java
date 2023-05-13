package bookPublisherProject.data.request.bookRequests;


import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.request.adminRequests.CreateAuthorRequest;
import lombok.Builder;


@Builder
public record CreateBookAndAuthorRequest(
        String name,
        String description,
        String releaseDate,
        //Yazarı belirtiyoruz.
        CreateAuthorRequest authorRequest) {

public Book convertToEntity() {
    return Book.builder()
            .name(name)
            .description(description)
            .releaseDate(releaseDate)
            .author(authorRequest.convertToEntity())
            .build();
}
}
