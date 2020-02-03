package challenge.avalith.service;

import challenge.avalith.expections.ResourceNotFoundException;
import challenge.avalith.model.Favorite;
import challenge.avalith.model.Launch;
import challenge.avalith.model.Tag;
import challenge.avalith.respository.FavoriteRepository;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepositoryMock;

    @InjectMocks
    private FavoriteService favoriteService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        Favorite favorite = new Favorite();
        favorite.setId(1L);
        when(favoriteRepositoryMock.findAll()).thenReturn(Arrays.asList(favorite));

        List<Favorite> favoritesFound = favoriteService.getAll();

        verify(favoriteRepositoryMock).findAll();
        assertEquals(1, favoritesFound.size());
        assertEquals(1l, favoritesFound.get(0).getId().longValue());
    }

    @Test
    public void testGetById() {
        Favorite favorite = new Favorite();
        favorite.setId(1L);
        when(favoriteRepositoryMock.findById(1l)).thenReturn(Optional.of(favorite));

        Favorite favoriteFound = favoriteService.getById(1l);

        verify(favoriteRepositoryMock).findById(1l);
        assertEquals(1l, favoriteFound.getId().longValue());
    }

    @Test
    public void testGetByIdThrowResourceNotFoundException() {
        Favorite favorite = new Favorite();
        favorite.setId(1L);
        when(favoriteRepositoryMock.findById(1l)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                favoriteService.getById(1l));

        verify(favoriteRepositoryMock).findById(1l);
        assertEquals("Favorite not found with id : 1", exception.getMessage());
    }

    @Test
    public void testCreate() {
        Launch launch = new Launch();
        launch.setFlightNumber(10);
        Tag tag = new Tag();
        tag.setText("Tag 1");
        List<Tag> tags = Arrays.asList(tag);
        tag.setId(1L);
        Favorite favorite = new Favorite(launch, tags);
        favorite.setId(1L);
        when(favoriteRepositoryMock.save(any(Favorite.class))).thenReturn(favorite);

        Favorite favoriteCreated = favoriteService.create(launch, tags);

        verify(favoriteRepositoryMock).save(any(Favorite.class));
        assertEquals(10, favoriteCreated.getLaunch().getFlightNumber());
        assertEquals("Tag 1", favoriteCreated.getTags().get(0).getText());
    }

    @Test
    public void testDelete() {
        Favorite favorite = new Favorite();
        favorite.setId(1L);
        when(favoriteRepositoryMock.findById(1l)).thenReturn(Optional.of(favorite));

        favoriteService.delete(1l);

        verify(favoriteRepositoryMock).findById(1l);
        verify(favoriteRepositoryMock).deleteById(1l);
    }
}
