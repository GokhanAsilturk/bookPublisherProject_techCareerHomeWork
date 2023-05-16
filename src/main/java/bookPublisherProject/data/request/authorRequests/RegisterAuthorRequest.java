package bookPublisherProject.data.request.authorRequests;

import bookPublisherProject.data.request.adminRequests.CreateAuthorRequest;
public record RegisterAuthorRequest (
        String name,
        String emailAddress,

        String password,

        String bio
){
    public CreateAuthorRequest convertToCreateAuthorRequest(){
       return CreateAuthorRequest.builder()
                .name(name)
                .emailAddress(emailAddress)
               .password(password)
                .bio(bio)
                .build();
    }
}
