package challenge.avalith.controller;

import challenge.avalith.model.Tag;
import challenge.avalith.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Api(value="Tags Controller", description = "Endpoints to manage tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation(value = "Get all tags")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = tagService.getAll();
        return ResponseEntity.ok(tags);
    }

    @ApiOperation(value = "Create tag")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag tagCreated = tagService.create(tag);
        return ResponseEntity.ok(tagCreated);
    }

    @ApiOperation(value = "Update tag by id")
    @RequestMapping(value = "/{tagId}", method = RequestMethod.PUT)
    public ResponseEntity<Tag> updateTag(@PathVariable Long tagId, @RequestBody Tag tag) {
        Tag tagUpdated = tagService.update(tagId, tag);
        return ResponseEntity.ok(tagUpdated);
    }

    @ApiOperation(value = "Delete a tag by id")
    @RequestMapping(value = "/{tagId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTag(@PathVariable Long tagId) {
        tagService.delete(tagId);
        return ResponseEntity.noContent().build();
    }
}
