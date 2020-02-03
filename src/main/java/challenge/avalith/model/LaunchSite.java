package challenge.avalith.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = LaunchSite.TABLE_NAME)
public class LaunchSite {
    public static final String TABLE_NAME = "launch_site";

    @Id
    @JsonProperty("site_id")
    private String id;

    @JsonProperty("site_name")
    private String siteName;

    @JsonProperty("site_name_long")
    private String siteNameLong;
}
