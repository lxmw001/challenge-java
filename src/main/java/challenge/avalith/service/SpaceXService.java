package challenge.avalith.service;

import challenge.avalith.model.Launch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpaceXService {
    public static final String SPACE_X_HOST = "https://api.spacexdata.com/v3/launches/";

    @Autowired
    private RestTemplate restTemplate;

//    public SpaceXService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }

    public Launch[] getUpcomingLaunches() {
        Launch[] response =  this.restTemplate.getForObject(SPACE_X_HOST + "upcoming?id=true", Launch[].class);
        return response;
    }

    public Launch getLaunchByFlightNumber(Integer flightNumber) {
        Launch response =  this.restTemplate.getForObject(SPACE_X_HOST + flightNumber + "?id=true", Launch.class);
        return response;
    }
}
