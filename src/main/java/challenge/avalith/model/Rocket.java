package challenge.avalith.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = Rocket.TABLE_NAME)
public class Rocket {
    public static final String TABLE_NAME = "rocket";

    @Id
    @JsonProperty("rocket_id")
    private String id;

    @JsonProperty("rocket_name")
    private String rocketName;
}
