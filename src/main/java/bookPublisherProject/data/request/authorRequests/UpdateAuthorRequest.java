package bookPublisherProject.data.request.authorRequests;

public record UpdateAuthorRequest(
        String authorId,
        String newAuthorName,
        String newEmailAddress,
        String newBio
) {
}
