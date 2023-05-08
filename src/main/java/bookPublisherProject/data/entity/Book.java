package bookPublisherProject.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@EqualsAndHashCode(callSuper = true)
@Document("books")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book extends BaseEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private String releaseDate;
    private Author author;

}
