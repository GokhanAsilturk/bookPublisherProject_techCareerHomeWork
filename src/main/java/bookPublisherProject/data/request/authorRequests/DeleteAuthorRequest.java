package bookPublisherProject.data.request.authorRequests;

public record DeleteAuthorRequest(
        int id,
        boolean permanentlyDelete
) {
}
