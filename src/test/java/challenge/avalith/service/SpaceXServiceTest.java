package challenge.avalith.service;

import challenge.avalith.model.Launch;
import challenge.avalith.model.Rocket;
import challenge.avalith.respository.RocketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpaceXServiceTest {

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private SpaceXService spaceXService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUpcomingLaunches() {
        Launch launch = new Launch();
        launch.setId("ID");
        Launch[] launches = {launch};
        when(restTemplateMock.getForObject(anyString(), any())).thenReturn(launches);

        Launch[] upcomingLunches = spaceXService.getUpcomingLaunches();

        verify(restTemplateMock).getForObject(SpaceXService.SPACE_X_HOST + "upcoming?id=true", Launch[].class);
        assertEquals(upcomingLunches, upcomingLunches);
    }

    @Test
    public void testGetLaunchByFlightNumber() {
        Launch launch = new Launch();
        launch.setId("ID");
        when(restTemplateMock.getForObject(anyString(), any())).thenReturn(launch);

        Launch launchFound = spaceXService.getLaunchByFlightNumber(1);

        verify(restTemplateMock).getForObject(SpaceXService.SPACE_X_HOST + 1 + "?id=true", Launch.class);
        assertEquals(launch, launchFound);
    }
}
