package bookPublisherProject.data.request.bookRequests;


import bookPublisherProject.data.request.authorRequests.CreateAuthorRequest;
import lombok.Builder;


@Builder
public record CreateBookRequest(
        String name,
        String description,
        String releaseDate,
        //Yazarı belirtiyoruz.
        CreateAuthorRequest authorRequest) {


}
