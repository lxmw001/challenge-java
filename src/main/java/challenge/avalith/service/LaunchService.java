package challenge.avalith.service;

import challenge.avalith.expections.ResourceAlreadyExistsException;
import challenge.avalith.model.Favorite;
import challenge.avalith.model.Launch;
import challenge.avalith.model.Tag;
import challenge.avalith.respository.LaunchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaunchService {

    private final SpaceXService spaceXService;

    private final LaunchRepository launchRepository;

    private final FavoriteService favoriteService;

    private final LaunchSiteService launchSiteService;

    private final RocketService rocketService;

    public Launch[] getUpcomingLaunches() {
        return spaceXService.getUpcomingLaunches();
    }

    public Optional<Launch> getByFlightNumber(Integer flightNumber) {
            return launchRepository.findOneByFlightNumber(flightNumber);
    }

    public void validateExistingLaunchFlightNumber(Integer flightNumber) {
        Optional<Launch> optionalLaunch = getByFlightNumber(flightNumber);
        if(optionalLaunch.isPresent()) {
            throw new ResourceAlreadyExistsException(Favorite.class, "launchNumber", flightNumber);
        }
    }

    public Launch create(Launch launch) {
        if(launch.getLaunchSite().getId() != null) {
            launchSiteService.createOrGet(launch.getLaunchSite());
        } else {
            launch.setLaunchSite(null);
        }
        if(launch.getRocket().getId() != null) {
            rocketService.createOrGet(launch.getRocket());
        } else {
            launch.setRocket(null);
        }
        return launchRepository.save(launch);
    }

    public Launch addToFavorites(Integer flightNumber, List<Tag> tags) {
        validateExistingLaunchFlightNumber(flightNumber);
        Launch externalLaunch = spaceXService.getLaunchByFlightNumber(flightNumber);
        Launch launchSaved = create(externalLaunch);
        favoriteService.create(launchSaved, tags);
        return launchSaved;
    }
}
