package wedontbyte.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wedontbyte.repository.rowmapper.IssueDtoRowMapper;
import wedontbyte.service.ApiRepositoryService;

/**
 * Created by mark on 10/09/2016.
 */
@Repository
public class ApiRepository implements ApiRepositoryService {

    private IssueDtoRowMapper issueDtoRowMapper;

    @Autowired
    public ApiRepository() {
        issueDtoRowMapper = new IssueDtoRowMapper();
    }

}
