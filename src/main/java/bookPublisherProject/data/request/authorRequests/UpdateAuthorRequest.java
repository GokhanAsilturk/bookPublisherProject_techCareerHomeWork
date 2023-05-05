package bookPublisherProject.data.request.authorRequests;

public record UpdateAuthorRequest(
        int authorId,
        String newAuthorName,
        String newEmailAddress,
        String newBio
) {
}
