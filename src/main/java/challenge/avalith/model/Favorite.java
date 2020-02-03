package challenge.avalith.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = Favorite.TABLE_NAME)
@NoArgsConstructor
public class Favorite {
    public static final String TABLE_NAME = "favorite";
    public static final String MANY_TO_MANY_TABLE_NAME = "favorite_tag";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(orphanRemoval = true)
    private Launch launch;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = MANY_TO_MANY_TABLE_NAME, joinColumns = { @JoinColumn(name = "favorite_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private List<Tag> tags;

    public Favorite(Launch launch, List<Tag> tags) {
        this.launch = launch;
        this.tags = tags;
    }
}
