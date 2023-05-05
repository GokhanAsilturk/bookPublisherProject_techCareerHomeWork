package bookPublisherProject.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("authors")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "authors")
public class Author extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String emailAddress;
    private String bio;

    @OneToMany(mappedBy = "authors")
    public List<Book> books;


}
