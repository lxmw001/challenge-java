package challenge.avalith.service;

import challenge.avalith.model.LaunchSite;
import challenge.avalith.respository.LaunchSiteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LaunchSiteServiceTest {

    @Mock
    private LaunchSiteRepository launchSiteRepositoryMock;

    @InjectMocks
    private LaunchSiteService launchSiteService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        LaunchSite launchSite = new LaunchSite();
        launchSite.setId("ID");
        when(launchSiteRepositoryMock.findById("ID")).thenReturn(Optional.of(launchSite));

        LaunchSite launchSiteFound = launchSiteService.getById("ID");

        verify(launchSiteRepositoryMock).findById("ID");
        assertEquals("ID", launchSiteFound.getId());
    }

    @Test
    public void testGetByIdReturnNull() {
        when(launchSiteRepositoryMock.findById("ID")).thenReturn(Optional.empty());

        LaunchSite launchSite = launchSiteService.getById("ID");

        verify(launchSiteRepositoryMock).findById("ID");
        assertEquals(null, launchSite);
    }

    @Test
    public void testCreateOrGet_Existing() {
        LaunchSite launchSite = new LaunchSite();
        launchSite.setId("ID");
        when(launchSiteRepositoryMock.findById("ID")).thenReturn(Optional.of(launchSite));

        LaunchSite launchSiteCreated = launchSiteService.createOrGet(launchSite);

        verify(launchSiteRepositoryMock).findById("ID");
        assertEquals("ID", launchSiteCreated.getId());
    }

    @Test
    public void testCreateOrGet_New() {
        LaunchSite launchSite = new LaunchSite();
        launchSite.setId("ID");
        when(launchSiteRepositoryMock.findById("ID")).thenReturn(Optional.empty());
        when(launchSiteRepositoryMock.save(launchSite)).thenReturn(launchSite);

        LaunchSite launchSiteCreated = launchSiteService.createOrGet(launchSite);

        verify(launchSiteRepositoryMock).findById("ID");
        verify(launchSiteRepositoryMock).save(launchSite);
        assertEquals("ID", launchSiteCreated.getId());
    }
}
