package bookPublisherProject.data.request.bookRequests;


import bookPublisherProject.data.entity.BookEntity;
import bookPublisherProject.data.request.adminRequests.CreateAuthorRequest;
import lombok.Builder;


@Builder
public record CreateBookAndAuthorRequest(
        String name,
        String description,
        String releaseDate,
        //Yazarı belirtiyoruz.
        CreateAuthorRequest authorRequest) {

    public BookEntity convertToEntity() {
        return BookEntity.builder()
                .name(name)
                .description(description)
                .releaseDate(releaseDate)
                .authorEntity(authorRequest.convertToEntity())
                .build();
    }
}
