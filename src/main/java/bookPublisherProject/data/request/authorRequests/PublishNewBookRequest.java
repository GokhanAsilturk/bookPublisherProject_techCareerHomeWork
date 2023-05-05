package bookPublisherProject.data.request.authorRequests;

public record PublishNewBookRequest(
        int authorId,
        String bookName,
        String description,
        String releaseDate
) {
}
