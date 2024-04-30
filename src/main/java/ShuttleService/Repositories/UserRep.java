package ShuttleService.Repositories;

import java.util.List;
import ShuttleService.Models.Users;
import ShuttleService.Models.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<Users,Integer> {

    List<Users> findUserBySUID(Integer SUID);

    Users findBySUID(Integer SUID);
    List<Users> findSUUserByAddress(String address);

    List<Users> findSUUserByName(String name);

    Users findByEmail(String email);

    List<Users> findSUUsersByUserStatus(UserStatus userStatus);

}
