package bookPublisherProject.data.request.authorRequests;

public record PublishNewBookRequest(
        String authorId,
        String bookName,
        String description,
        String releaseDate
) {
}
