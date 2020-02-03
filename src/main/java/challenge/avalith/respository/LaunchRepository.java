package challenge.avalith.respository;

import challenge.avalith.model.Launch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaunchRepository extends JpaRepository<Launch, String> {

    Optional<Launch> findOneByFlightNumber(Integer flightNumber);
}
