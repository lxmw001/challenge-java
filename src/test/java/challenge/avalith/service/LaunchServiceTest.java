package challenge.avalith.service;

import challenge.avalith.expections.ResourceAlreadyExistsException;
import challenge.avalith.model.Launch;
import challenge.avalith.model.LaunchSite;
import challenge.avalith.model.Rocket;
import challenge.avalith.model.Tag;
import challenge.avalith.respository.LaunchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LaunchServiceTest {

    @Mock
    private LaunchRepository launchRepositoryMock;

    @Mock
    private SpaceXService spaceXServiceMock;

    @Mock
    private FavoriteService favoriteServiceMock;

    @Mock
    private LaunchSiteService launchSiteServiceMock;

    @Mock
    private RocketService rocketServiceMock;

    @InjectMocks
    private LaunchService launchService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUpcomingLaunches() {
        Launch launch = new Launch();
        launch.setId("ID");
        Launch[] launches = {launch};
        when(spaceXServiceMock.getUpcomingLaunches()).thenReturn(launches);

        Launch[] launchesFound = launchService.getUpcomingLaunches();

        verify(spaceXServiceMock).getUpcomingLaunches();
        assertEquals(1, launches.length);
        assertEquals("ID", launches[0].getId());
    }


    @Test
    public void testGetByFlightNumber_present() {
        Launch launch = new Launch();
        launch.setFlightNumber(1);
        launch.setId("ID");
        when(launchRepositoryMock.findOneByFlightNumber(1)).thenReturn(Optional.of(launch));

        Optional<Launch> launchFound = launchService.getByFlightNumber(1);

        verify(launchRepositoryMock).findOneByFlightNumber(1);
        assertTrue("Launch present", launchFound.isPresent());
        assertEquals(1, launchFound.get().getFlightNumber());
        assertEquals("ID", launchFound.get().getId());
    }

    @Test
    public void testGetByFlightNumber_noPresent() {
        when(launchRepositoryMock.findOneByFlightNumber(1)).thenReturn(Optional.empty());

        Optional<Launch> launchFound = launchService.getByFlightNumber(1);

        verify(launchRepositoryMock).findOneByFlightNumber(1);
        assertFalse("Launch not present", launchFound.isPresent());
    }

    @Test
    public void testValidateExistingLaunchFlightNumber() {
        Launch launch = new Launch();
        launch.setFlightNumber(1);
        launch.setId("ID");
        when(launchRepositoryMock.findOneByFlightNumber(1)).thenReturn(Optional.of(launch));

        Exception exception = assertThrows(ResourceAlreadyExistsException.class, () ->
                launchService.validateExistingLaunchFlightNumber(1));

        verify(launchRepositoryMock).findOneByFlightNumber(1);
        assertEquals("Favorite already exists for launchNumber : 1", exception.getMessage());
    }

    @Test
    public void testCreate_nullIds() {
        LaunchSite launchSite = new LaunchSite();
        Rocket rocket = new Rocket();
        Launch launch = new Launch();
        launch.setId("ID");
        launch.setLaunchSite(launchSite);
        launch.setRocket(rocket);
        when(launchRepositoryMock.save(any())).thenReturn(launch);

        Launch launchCreated = launchService.create(launch);

        Launch expectedLaunch = launch;
        expectedLaunch.setLaunchSite(null);
        expectedLaunch.setRocket(null);

        verify(launchRepositoryMock).save(expectedLaunch);
        assertEquals(launchCreated, expectedLaunch);
    }

    @Test
    public void testCreate_validIds() {
        LaunchSite launchSite = new LaunchSite();
        launchSite.setId("launchSiteId");
        Rocket rocket = new Rocket();
        rocket.setId("rocketId");
        Launch launch = new Launch();
        launch.setId("ID");
        launch.setLaunchSite(launchSite);
        launch.setRocket(rocket);
        when(launchRepositoryMock.save(any())).thenReturn(launch);
        when(launchSiteServiceMock.createOrGet(launchSite)).thenReturn(launchSite);
        when(rocketServiceMock.createOrGet(rocket)).thenReturn(rocket);

        Launch launchCreated = launchService.create(launch);

        verify(launchSiteServiceMock).createOrGet(launchSite);
        verify(rocketServiceMock).createOrGet(rocket);
        assertEquals(launchCreated, launch);
    }

    @Test
    public void testAddToFavorites() {
        Tag tag = new Tag();
        tag.setId(1L);
        List<Tag> tags = Arrays.asList(tag);

        LaunchSite launchSite = new LaunchSite();
        launchSite.setId("launchSiteId");
        Rocket rocket = new Rocket();
        rocket.setId("rocketId");
        Launch launch = new Launch();
        launch.setId("ID");
        launch.setFlightNumber(1);
        launch.setLaunchSite(launchSite);
        launch.setRocket(rocket);

        when(launchRepositoryMock.findOneByFlightNumber(1)).thenReturn(Optional.empty());
        when(spaceXServiceMock.getLaunchByFlightNumber(1)).thenReturn(launch);
        when(launchRepositoryMock.save(any())).thenReturn(launch);

        Launch launchCreated = launchService.addToFavorites(1, tags);

        verify(launchRepositoryMock).findOneByFlightNumber(1);
        verify(spaceXServiceMock).getLaunchByFlightNumber(1);
        verify(launchRepositoryMock).save(launch);
        verify(favoriteServiceMock).create(launch, tags);
        assertEquals(launchCreated, launch);
    }
}
