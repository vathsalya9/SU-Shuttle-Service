package SUShuttleService.SURepository;

import org.springframework.data.jpa.repository.JpaRepository;
import SUShuttleService.SUBackEnd.SUShuttleUser;

    public interface ShuttleUserRep extends JpaRepository<SUShuttleUser,Integer> {

    }

