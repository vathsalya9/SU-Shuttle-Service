package ShuttleService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ShuttleService.Models.ShuttleLocation;
import org.springframework.stereotype.Repository;

@Repository
public interface ShuttleRep extends JpaRepository<ShuttleLocation,Integer>{

}
