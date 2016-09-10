package wedontbyte.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wedontbyte.data.Location;
import wedontbyte.data.Type;
import wedontbyte.dto.IssueDto;
import wedontbyte.service.ApiService;

import java.util.List;

@RestController
public class ResultsController {

    private Logger LOGGER = Logger.getLogger(ResultsController.class);

    private ApiService apiService;

    @Autowired
    public ResultsController(ApiService apiService) {
        this.apiService = apiService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<IssueDto>> getAll(@RequestParam("timestamp") Long timestamp) {
        try {
            return ResponseEntity.ok(apiService.retrieveAll(timestamp));
        } catch (Exception e) {
            LOGGER.error(e.getMessage().toString());
            return new ResponseEntity<List<IssueDto>>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/allByLocation", method = RequestMethod.GET)
    public ResponseEntity<List<IssueDto>> getAllByLocation(@RequestParam float latitude,
                                                           @RequestParam float longitude,
                                                           @RequestParam Long timestamp) {
        try {
            return ResponseEntity.ok(apiService.retrieveAllFilterByLocation(new Location(latitude, longitude), timestamp));
        } catch (Exception e) {
            LOGGER.error(e.getMessage().toString());
            return new ResponseEntity<List<IssueDto>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/allByType", method = RequestMethod.GET)
    public ResponseEntity<List<IssueDto>> getType(@RequestParam Type type,
                                                  @RequestParam Long timestamp) {
        try {
            return ResponseEntity.ok(apiService.retrieveAllFilterByType(type, timestamp));
        } catch (Exception e) {
            LOGGER.error(e.getMessage().toString());
            return new ResponseEntity<List<IssueDto>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/allByLocationType", method = RequestMethod.GET)
    public ResponseEntity<List<IssueDto>> getTypeByLocationAndType(@RequestParam float latitude,
                                                                   @RequestParam float longitude,
                                                                   @RequestParam Type type,
                                                                   @RequestParam Long timestamp) {
        try {
            return ResponseEntity.ok(
                    apiService.retrieveAllFilterByLocationAndType(type, new Location(latitude, longitude), timestamp));
        } catch (Exception e) {
            LOGGER.error(e.getMessage().toString());
            return new ResponseEntity<List<IssueDto>>(HttpStatus.BAD_REQUEST);
        }
    }

}
