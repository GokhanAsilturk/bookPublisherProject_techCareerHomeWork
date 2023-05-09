package bookPublisherProject.data.request.authorRequests;

public record PublishNewBookRequest(

        String name,
        String description,
        String releaseDate,
        String authorId
) {
}
