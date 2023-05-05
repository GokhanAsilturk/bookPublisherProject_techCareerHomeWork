package bookPublisherProject.data.request.bookRequests;

import lombok.Builder;

@Builder
public record CreateBookRequest (
        int authorId,
        String name,
        String description,
        String releaseDate
){
}
