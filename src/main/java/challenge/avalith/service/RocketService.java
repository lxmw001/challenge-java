package challenge.avalith.service;

import challenge.avalith.model.Rocket;
import challenge.avalith.respository.RocketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RocketService {

    private final RocketRepository rocketRepository;

    public Rocket getById(String rocketId) {
        Optional<Rocket> optionalRocket = rocketRepository.findById(rocketId);
        return optionalRocket.orElse(null);
    }

    public Rocket createOrGet(Rocket rocket) {
        Rocket current = getById(rocket.getId());
        if (current != null) {
            return rocket;
        }
        return rocketRepository.save(rocket);
    }
}
