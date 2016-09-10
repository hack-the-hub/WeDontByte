package wedontbyte.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Location {

    @JsonProperty("latitude")
    private float latitude;
    @JsonProperty("longitude")
    private float longitude;

    public Location(
            @JsonProperty("latitude") float latitude,
            @JsonProperty("longitude") float longitude
    ) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
