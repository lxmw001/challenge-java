package challenge.avalith.respository;

import challenge.avalith.model.Rocket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RocketRepository extends JpaRepository<Rocket, String> {
}
