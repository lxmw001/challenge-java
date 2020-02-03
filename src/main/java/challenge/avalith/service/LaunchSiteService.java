package challenge.avalith.service;

import challenge.avalith.model.LaunchSite;
import challenge.avalith.respository.LaunchSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaunchSiteService {

    private final LaunchSiteRepository launchSiteRepository;

    public LaunchSite getById(String launchSiteId) {
        Optional<LaunchSite> optionalLaunchSite = launchSiteRepository.findById(launchSiteId);
        return optionalLaunchSite.orElse(null);
    }

    public LaunchSite createOrGet(LaunchSite launchSite) {
        LaunchSite current = getById(launchSite.getId());
        if (current != null) {
            return launchSite;
        }
        return launchSiteRepository.save(launchSite);
    }
}
