package bookPublisherProject.data.request.authorRequests;

import bookPublisherProject.data.entity.users.AuthorEntity;

public record UpdateAuthorRequest(
        String authorId,
        String newAuthorName,
        String newEmailAddress,
        String newBio
) {

    public AuthorEntity convertToEntity() {
        return AuthorEntity.builder()
                .id(authorId)
                .name(newAuthorName)
                .emailAddress(newEmailAddress)
                .bio(newBio)
                .build();

    }
}
