package bookPublisherProject.data.request.bookRequests;

import lombok.Builder;

@Builder
public record CreateBookRequest (
        String authorId,
        String name,
        String description,
        String releaseDate
){
}
