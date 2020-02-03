package challenge.avalith.service;

import challenge.avalith.expections.ResourceNotFoundException;
import challenge.avalith.model.Launch;
import challenge.avalith.model.Favorite;
import challenge.avalith.model.Tag;
import challenge.avalith.respository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public List<Favorite> getAll() {
        return favoriteRepository.findAll();
    }

    public Favorite getById(Long favoriteId) {
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(favoriteId);
        return optionalFavorite.orElseThrow(() -> new ResourceNotFoundException(Favorite.class, "id", favoriteId));
    }

        public Favorite create(Launch launch, List<Tag> tags) {
        Favorite favorite = new Favorite(launch, tags);
        favoriteRepository.save(favorite);
        return favorite;
    }

    public void delete(Long favoriteId) {
        getById(favoriteId);
        favoriteRepository.deleteById(favoriteId);
    }
}
