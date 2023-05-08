package bookPublisherProject.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("authors")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Author extends BaseEntity {

    @Id
    private String id;
    private String name;
    private String emailAddress;
    private String bio;
    private List<Book> books;


}
