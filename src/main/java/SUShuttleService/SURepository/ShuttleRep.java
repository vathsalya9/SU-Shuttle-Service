package SUShuttleService.SURepository;

import org.springframework.data.jpa.repository.JpaRepository;
import SUShuttleService.SUBackEnd.SUShuttleLocation;

public interface ShuttleRep extends JpaRepository<SUShuttleLocation,Integer>{

}
