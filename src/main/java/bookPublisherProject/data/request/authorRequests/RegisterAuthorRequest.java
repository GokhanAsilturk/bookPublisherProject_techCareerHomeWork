package bookPublisherProject.data.request.authorRequests;

public record RegisterAuthorRequest (
        String name,
        String emailAddress,
        String bio
){
    public CreateAuthorRequest convertToCreateAuthorRequest(){
       return CreateAuthorRequest.builder()
                .name(name)
                .emailAddress(emailAddress)
                .bio(bio)
                .build();
    }
}
