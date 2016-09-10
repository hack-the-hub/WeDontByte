package wedontbyte.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import wedontbyte.data.Location;
import wedontbyte.data.Type;
import wedontbyte.dto.IssueDto;
import wedontbyte.repository.rowmapper.IssueDtoRowMapper;
import wedontbyte.service.ApiRepositoryService;

import java.util.List;

/**
 * Created by mark on 10/09/2016.
 */
@Repository
public class ApiRepository implements ApiRepositoryService {

    private IssueDtoRowMapper issueDtoRowMapper;

    private Logger LOGGER = Logger.getLogger(ApiRepository.class);

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ApiRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        issueDtoRowMapper = new IssueDtoRowMapper();
    }


    @Override
    public void createData(IssueDto issueDto) {

        String sql = "INSERT INTO issues (issue_id, type, rating, comment, issue_time) " +
                "VALUES (:issue_id, :type, :rating, :comment, :issue_time)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("issue_id", issueDto.getIssueId())
                .addValue("type", issueDto.getType().name())
                .addValue("rating", issueDto.getRating())
                .addValue("comment", issueDto.getComment())
                .addValue("issue_time", issueDto.getIssueTime());

        jdbcTemplate.update(sql,
                mapSqlParameterSource);

        saveGeolocationData(issueDto.getIssueId(), issueDto.getLocation());
    }

    public void saveGeolocationData(String issueId, Location location) {

        String sql = "INSERT INTO geolocation (geolocation_issue_id, latitude, longitude) " +
                "VALUES (:geolocation_issue_id, :latitude, :longitude)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("geolocation_issue_id", issueId)
                .addValue("latitude", location.getLatitude())
                .addValue("longitude", location.getLongitude());

        jdbcTemplate.update(sql,
                mapSqlParameterSource);
    }

    @Override
    public List<IssueDto> retrieveAll(Long timestamp) {

        String sql = "SELECT * " +
                "FROM issues i " +
                "JOIN geolocation g ON (i.issue_id = g.geolocation_issue_id) " +
                "WHERE i.issue_time > :timestamp";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("timestamp", timestamp);

        List<IssueDto> issueDtoList = jdbcTemplate.query(sql,
                mapSqlParameterSource,
                issueDtoRowMapper
        );

        return issueDtoList;
    }

    @Override
    public List<IssueDto> retrieveAllFilterByType(Type type, Long timestamp) {
        String sql = "SELECT * " +
                "FROM issues i " +
                "JOIN geolocation g ON (i.issue_id = g.geolocation_issue_id) " +
                "WHERE i.issue_time > :timestamp " +
                "AND i.type = :type";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("timestamp", timestamp)
                .addValue("type", type.name());

        List<IssueDto> issueDtoList = jdbcTemplate.query(sql,
                mapSqlParameterSource,
                issueDtoRowMapper
        );

        return issueDtoList;
    }

    @Override
    public List<IssueDto> retrieveAllFilterByLocation(Location location, Long timestamp) {
        String sql = "SELECT * " +
                "FROM issues i " +
                "JOIN geolocation g ON (i.issue_id = g.geolocation_issue_id) " +
                "WHERE i.issue_time > :timestamp " +
                "AND g.latitude = :latitude " +
                "AND g.longitude = :longitude";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("timestamp", timestamp)
                .addValue("latitude", location.getLatitude())
                .addValue("longitude", location.getLongitude());

        List<IssueDto> issueDtoList = jdbcTemplate.query(sql,
                mapSqlParameterSource,
                issueDtoRowMapper
        );

        return issueDtoList;
    }

    @Override
    public List<IssueDto> retrieveAllFilterByLocationAndType(Type type, Location location, Long timestamp) {
        String sql = "SELECT * " +
                "FROM issues i " +
                "JOIN geolocation g ON (i.issue_id = g.geolocation_issue_id) " +
                "WHERE i.issue_time > :timestamp " +
                "AND i.type = :type " +
                "AND g.latitude = :latitude " +
                "AND g.longitude = :longitude";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("timestamp", timestamp)
                .addValue("type", type.name())
                .addValue("latitude", location.getLatitude())
                .addValue("longitude", location.getLongitude());

        List<IssueDto> issueDtoList = jdbcTemplate.query(sql,
                mapSqlParameterSource,
                issueDtoRowMapper
        );

        return issueDtoList;
    }

}
