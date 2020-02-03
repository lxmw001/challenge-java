package challenge.avalith.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = Launch.TABLE_NAME)
public class Launch {
    public static final String TABLE_NAME = "launch";

    @Id
    @JsonProperty("_id")
    private String id;

    @JsonProperty("flight_number")
    private int flightNumber;

    @JsonProperty("mission_name")
    private String missionName;

    @JsonProperty("launch_date_utc")
    private Date launchDate;

    @JoinColumn
    @JsonProperty("launch_site")
    @ManyToOne(cascade = CascadeType.DETACH)
    private LaunchSite launchSite;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.DETACH)
    private Rocket rocket;
}

