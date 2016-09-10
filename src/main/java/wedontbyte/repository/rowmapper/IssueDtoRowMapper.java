package wedontbyte.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import wedontbyte.data.Location;
import wedontbyte.data.Type;
import wedontbyte.dto.IssueDto;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mark on 10/09/2016.
 */
public class IssueDtoRowMapper implements RowMapper {

    @Override
    public IssueDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return new IssueDto(
                resultSet.getString("issue_id"),
                new Location(resultSet.getFloat("latitude"), resultSet.getFloat("longitude")),
                convertStringToEnum(resultSet.getString("type")),
                resultSet.getString("comment"),
                resultSet.getInt("rating"),
                resultSet.getLong("issue_time")
        );
    }

    private Type convertStringToEnum(String type) {
        return Type.valueOf(type);
    }

}
