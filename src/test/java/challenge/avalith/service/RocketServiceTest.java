package challenge.avalith.service;

import challenge.avalith.model.Rocket;
import challenge.avalith.respository.RocketRepository;
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
public class RocketServiceTest {

    @Mock
    private RocketRepository rocketRepositoryMock;

    @InjectMocks
    private RocketService rocketService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        Rocket rocket = new Rocket();
        rocket.setId("ID");
        when(rocketRepositoryMock.findById("ID")).thenReturn(Optional.of(rocket));

        Rocket rocketFound = rocketService.getById("ID");

        verify(rocketRepositoryMock).findById("ID");
        assertEquals("ID", rocketFound.getId());
    }

    @Test
    public void testGetByIdReturnNull() {
        when(rocketRepositoryMock.findById("ID")).thenReturn(Optional.empty());

        Rocket rocket = rocketService.getById("ID");

        verify(rocketRepositoryMock).findById("ID");
        assertEquals(null, rocket);
    }

    @Test
    public void testCreateOrGet_Existing() {
        Rocket rocket = new Rocket();
        rocket.setId("ID");
        when(rocketRepositoryMock.findById("ID")).thenReturn(Optional.of(rocket));

        Rocket rocketCreated = rocketService.createOrGet(rocket);

        verify(rocketRepositoryMock).findById("ID");
        assertEquals("ID", rocketCreated.getId());
    }

    @Test
    public void testCreateOrGet_New() {
        Rocket rocket = new Rocket();
        rocket.setId("ID");
        when(rocketRepositoryMock.findById("ID")).thenReturn(Optional.empty());
        when(rocketRepositoryMock.save(rocket)).thenReturn(rocket);

        Rocket rocketCreated = rocketService.createOrGet(rocket);

        verify(rocketRepositoryMock).findById("ID");
        verify(rocketRepositoryMock).save(rocket);
        assertEquals("ID", rocketCreated.getId());
    }
}
