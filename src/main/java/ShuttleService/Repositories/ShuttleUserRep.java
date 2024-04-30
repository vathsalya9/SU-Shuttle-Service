package ShuttleService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ShuttleService.Models.ShuttleUsers;
import org.springframework.stereotype.Repository;

@Repository
    public interface ShuttleUserRep extends JpaRepository<ShuttleUsers,Integer> {

    }

