package bookPublisherProject.data.entity.users;


import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.dto.UserDto;
import bookPublisherProject.data.entity.Book;
import bookPublisherProject.data.entity.baseEntitties.User;
import bookPublisherProject.data.types.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("authors")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Author extends User {

    private String name;
    private String bio;
    private List<Book> books;


    public AuthorDto convertToDto() {
        return AuthorDto.builder()
                .name(name)
                .emailAddress(getEmailAddress())
                .bio(bio)
                .build();
    }

    public UserDto convertToUserDto(){
        return UserDto.builder()
                .emailAddress(getEmailAddress())
                .userType(UserType.AUTHOR)
                .build();
    }



}
