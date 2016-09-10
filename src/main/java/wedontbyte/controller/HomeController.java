package wedontbyte.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wedontbyte.data.Location;
import wedontbyte.data.Type;
import wedontbyte.dto.IssueDto;
import wedontbyte.service.ApiService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;


@RestController
public class HomeController {

    private ApiService apiService;

    private Logger LOGGER = Logger.getLogger(HomeController.class);

    @Autowired
    public HomeController(ApiService apiService) {
        this.apiService = apiService;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> create(HttpServletRequest request) {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            String[] test = parameterMap.get("issue");
            String typeString = test[0];
            Type type = Type.valueOf("litter");
            IssueDto issueDto = new IssueDto(
                    UUID.randomUUID().toString(),
                    new Location(1.0f, 2.0f),
                    Type.valueOf(parameterMap.get("issue")[0]),
                    parameterMap.get("comments")[0],
                    5,
                    System.currentTimeMillis());

            issueDto.setIssueTime(System.currentTimeMillis());
            apiService.createData(issueDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage().toString());
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

}

