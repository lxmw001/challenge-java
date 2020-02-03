package challenge.avalith.service;

import challenge.avalith.expections.ResourceNotFoundException;
import challenge.avalith.model.Tag;
import challenge.avalith.respository.TagRepository;
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

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {

    @Mock
    private TagRepository tagRepositoryMock;

    @InjectMocks
    private TagService tagService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        Tag tag = new Tag();
        tag.setId(1L);
        when(tagRepositoryMock.findAll()).thenReturn(Arrays.asList(tag));

        List<Tag> tagsFound = tagService.getAll();

        verify(tagRepositoryMock).findAll();
        assertEquals(1, tagsFound.size());
        assertEquals(1l, tagsFound.get(0).getId().longValue());
    }

    @Test
    public void testGetById() {
        Tag tag = new Tag();
        tag.setId(1L);
        when(tagRepositoryMock.findById(1l)).thenReturn(Optional.of(tag));

        Tag tagFound = tagService.getById(1l);

        verify(tagRepositoryMock).findById(1l);
        assertEquals(1l, tagFound.getId().longValue());
    }

    @Test
    public void testGetByIdThrowResourceNotFoundException() {
        when(tagRepositoryMock.findById(1l)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                tagService.getById(1l));

        verify(tagRepositoryMock).findById(1l);
        assertEquals("Tag not found with id : 1", exception.getMessage());
    }

    @Test
    public void testCreate() {
        Tag tag = new Tag();
        tag.setId(1L);
        when(tagRepositoryMock.save(tag)).thenReturn(tag);

        Tag tagCreated = tagService.create(tag);

        verify(tagRepositoryMock).save(tag);
        assertEquals(1l, tagCreated.getId().longValue());
    }

    @Test
    public void testUpdate() {
        Tag currentTag = new Tag();
        currentTag.setId(1L);
        Tag toUpdateTag = new Tag();
        toUpdateTag.setId(1L);
        toUpdateTag.setText("Updated");
        when(tagRepositoryMock.save(toUpdateTag)).thenReturn(toUpdateTag);

        Tag tagUpdated = tagService.create(toUpdateTag);

        verify(tagRepositoryMock).save(toUpdateTag);
        assertEquals(1l, tagUpdated.getId().longValue());
        assertEquals("Updated", tagUpdated.getText());
    }

    @Test
    public void testDelete() {
        Tag tag = new Tag();
        tag.setId(1L);
        when(tagRepositoryMock.findById(1l)).thenReturn(Optional.of(tag));

        tagService.delete(1l);

        verify(tagRepositoryMock).findById(1l);
        verify(tagRepositoryMock).deleteById(1l);
    }
}
