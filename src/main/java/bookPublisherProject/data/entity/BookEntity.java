package bookPublisherProject.data.entity;

import bookPublisherProject.data.dto.AuthorDto;
import bookPublisherProject.data.dto.BookDto;
import bookPublisherProject.data.entity.baseEntitties.ItemEntity;
import bookPublisherProject.data.entity.users.AuthorEntity;
import bookPublisherProject.data.types.ItemType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@EqualsAndHashCode(callSuper = true)
@Document("bookEntities")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookEntity extends ItemEntity {

    @Builder.Default
    private final ItemType itemType = ItemType.BOOK;
    private AuthorEntity authorEntity;

    public BookDto convertToDto() {
        return BookDto.builder()
                .name(getName())
                .description(getDescription())
                .releaseDate(getReleaseDate())
                .authorDto(AuthorDto.builder()
                        .name(authorEntity.getName())
                        .emailAddress(authorEntity.getEmailAddress())
                        .bio(authorEntity.getBio())
                        .build())
                .build();
    }

}
