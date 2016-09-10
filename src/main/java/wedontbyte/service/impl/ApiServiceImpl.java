package wedontbyte.service.impl;

import com.google.common.io.BaseEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wedontbyte.data.Location;
import wedontbyte.data.Type;
import wedontbyte.dto.IssueDto;
import wedontbyte.service.ApiRepositoryService;
import wedontbyte.service.ApiService;

import java.io.UnsupportedEncodingException;
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
        try {
        issueDto.setComment(encodeData(issueDto.getComment()));
        apiRepository.createData(issueDto);
        } catch (Exception e) {

        }
    }

    @Override
    public List<IssueDto> retrieveAll(Long timestamp) {
        try {
            List<IssueDto> issueDtos = apiRepository.retrieveAll(timestamp);
            for (IssueDto issue : issueDtos) {
                issue.setComment(decodeData(issue.getComment()));
            }
            return issueDtos;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<IssueDto> retrieveAllFilterByType(Type type, Long timestamp) {

        try {
            List<IssueDto> issueDtos = apiRepository.retrieveAllFilterByType(type, timestamp);
            for (IssueDto issue : issueDtos) {
                issue.setComment(decodeData(issue.getComment()));
            }
            return issueDtos;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<IssueDto> retrieveAllFilterByLocation(Location location, Long timestamp) {
        try {
            List<IssueDto> issueDtos = apiRepository.retrieveAllFilterByLocation(location, timestamp);
            for (IssueDto issue : issueDtos) {
                issue.setComment(decodeData(issue.getComment()));
            }
            return issueDtos;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<IssueDto> retrieveAllFilterByLocationAndType(Type type, Location location, Long timestamp) {
        try {
            List<IssueDto> issueDtos = apiRepository.retrieveAllFilterByLocationAndType(type, location, timestamp);
            for (IssueDto issue : issueDtos) {
                issue.setComment(decodeData(issue.getComment()));
            }
            return issueDtos;
        } catch (Exception e) {
            return null;
        }
    }

    private String encodeData(String comment) throws UnsupportedEncodingException {
        return BaseEncoding.base64().encode(comment.getBytes("UTF-8"));
    }

    private String decodeData(String comment) throws UnsupportedEncodingException {
        byte[] contentInBytes = BaseEncoding.base64().decode(comment);
        return new String(contentInBytes, "UTF-8");
    }

}
