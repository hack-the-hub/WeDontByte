package wedontbyte.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wedontbyte.data.Location;
import wedontbyte.data.Type;
import wedontbyte.dto.IssueDto;
import wedontbyte.service.ApiRepositoryService;
import wedontbyte.service.ApiService;

import java.util.List;

/**
 * Created by mark on 10/09/2016.
 */
@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;

    private ApiRepositoryService apiRepository;

    @Autowired
    public ApiServiceImpl(RestTemplate restTemplate, ApiRepositoryService apiRepository) {
        this.restTemplate = restTemplate;
        this.apiRepository = apiRepository;
    }


    @Override
    public void createData(IssueDto issueDto) {
        apiRepository.createData(issueDto);
    }

    @Override
    public List<IssueDto> retrieveAll(Long timestamp) {
        return apiRepository.retrieveAll(timestamp);
    }

    @Override
    public List<IssueDto> retrieveAllFilterByType(Type type, Long timestamp) {
        return apiRepository.retrieveAllFilterByType(type, timestamp);
    }

    @Override
    public List<IssueDto> retrieveAllFilterByLocation(Location location, Long timestamp) {
        return apiRepository.retrieveAllFilterByLocation(location, timestamp);
    }

    @Override
    public List<IssueDto> retrieveAllFilterByLocationAndType(Type type, Location location, Long timestamp) {
        return apiRepository.retrieveAllFilterByLocationAndType(type, location, timestamp);
    }

}
