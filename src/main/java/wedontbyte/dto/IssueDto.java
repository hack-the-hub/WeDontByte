package wedontbyte.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import wedontbyte.data.Location;
import wedontbyte.data.Type;

/**
 * Created by mark on 10/09/2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class IssueDto {

    @JsonProperty("issueId")
    private String issueId;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("type")
    private Type type;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("rating")
    private int rating;
    @JsonProperty("issueTime")
    private long issueTime;

    @Builder
    public IssueDto (
            @JsonProperty("issueId") String issueId,
            @JsonProperty("location") Location location,
            @JsonProperty("type") Type type,
            @JsonProperty("comment") String comment,
            @JsonProperty("rating") int rating,
            @JsonProperty("issueTime") long issueTime
    ) {
        this.issueId = issueId;
        this.location = location;
        this.type = type;
        this.comment = comment;
        this.rating = rating;
        this.issueTime = issueTime;
    }


}
