package challenge.avalith.service;

import challenge.avalith.expections.ResourceNotFoundException;
import challenge.avalith.model.Tag;
import challenge.avalith.respository.TagRepository;
import challenge.avalith.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag getById(Long tagId) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        return optionalTag.orElseThrow(() -> new ResourceNotFoundException(Tag.class, "id", tagId));
    }

    public Tag create(Tag tag) {
        Tag tagCreated = tagRepository.save(tag);
        return tagCreated;
    }

    public Tag update(Long tagId, Tag tag) throws RuntimeException {
        Tag current = getById(tagId);
        ObjectUtils.copyObject(tag, current);
        return tagRepository.save(tag);
    }

    public void delete(Long tagId) {
        getById(tagId);
        tagRepository.deleteById(tagId);
    }
}
