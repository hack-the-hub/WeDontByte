package wedontbyte.service;


import wedontbyte.data.Location;
import wedontbyte.data.Type;
import wedontbyte.dto.IssueDto;

import java.util.List;

public interface ApiService {

    void createData(IssueDto issueDto);

    List<IssueDto> retrieveAll(Long timestamp);

    List<IssueDto> retrieveAllFilterByType(Type type, Long timestamp);

    List<IssueDto> retrieveAllFilterByLocation(Location location, Long timestamp);

    List<IssueDto> retrieveAllFilterByLocationAndType(Type type, Location location, Long timestamp);
}
