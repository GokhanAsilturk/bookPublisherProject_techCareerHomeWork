package bookPublisherProject.data.entity.baseEntitties;


import bookPublisherProject.data.types.EntityType;
import bookPublisherProject.data.types.ItemType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("items")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ItemEntity extends BaseEntity {
    @Builder.Default
    private final EntityType entityType = EntityType.ITEM;
    @Id
    private String id;
    private String name;
    private String description;
    private String releaseDate;
    private ItemType itemType;
}
