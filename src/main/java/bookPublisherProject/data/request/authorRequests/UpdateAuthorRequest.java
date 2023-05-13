package bookPublisherProject.data.request.authorRequests;

import bookPublisherProject.data.entity.users.Author;

public record UpdateAuthorRequest(
        String authorId,
        String newAuthorName,
        String newEmailAddress,
        String newBio
) {

    public Author convertToEntity() {
        return Author.builder()
                .id(authorId)
                .name(newAuthorName)
                .emailAddress(newEmailAddress)
                .bio(newBio)
                .build();

    }
}
