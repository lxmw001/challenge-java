package challenge.avalith.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = Tag.TABLE_NAME)
public class Tag {
    public static final String TABLE_NAME = "tag";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
}
