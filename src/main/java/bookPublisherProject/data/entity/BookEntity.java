package bookPublisherProject.data.entity;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.baseEntitties.BaseEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@EqualsAndHashCode(callSuper = true)
@Document("bookEntities")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookEntity extends BaseEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private String releaseDate;

    private AuthorEntity authorEntity;


    public BookDto convertToDto(){
        return BookDto.builder()
                .name(name)
                .description(description)
                .releaseDate(releaseDate)
                .authorDto(AuthorDto.builder()
                        .name(authorEntity.getName())
                        .emailAddress(authorEntity.getEmailAddress())
                        .bio(authorEntity.getBio())
                        .build())
                .build();
    }

}
