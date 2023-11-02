package SUShuttleService.SURepository;

import java.util.*;
import java.util.List;
import SUShuttleService.SUBackEnd.SUUser;
import SUShuttleService.SUBackEnd.SUUserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<SUUser,Integer> {

    List<SUUser> findSUUserBySUID(Integer suid);

    SUUser findBySUID(Integer SUID);
    List<SUUser> findSUUserByAddress(String Address);

    List<SUUser> findSUUserByName(String name);

    SUUser findByEmail(String email);

    List<SUUser> findSUUsersByUserStatus(SUUserStatus userStatus);

}
