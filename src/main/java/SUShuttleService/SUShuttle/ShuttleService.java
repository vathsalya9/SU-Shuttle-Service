package SUShuttleService.SUShuttle;

import java.util.*;
import SUShuttleService.SURepository.*;
import SUShuttleService.SUBackEnd.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShuttleService {
    @Autowired
    private UserRep userrepo;
    private ShuttleUserRep shuttleuser;
    private ShuttleRep shuttlerep;
    private int ETAS1,ETAS2;

    public String UserEntry(Integer suid, String name, String email, String address) {
        if (!isValidSuidLength(suid)) {
            return "Please enter a valid 10-digit SUID";
        }
        if (!userrepo.findSUUserBySUID(suid).isEmpty() ) {
            return "The SUID already exists";
        }
        if (!email.endsWith("syr.edu")) {
            return "Please enter a valid SU email";
        }
        if (userrepo.findByEmail(email) != null) {
            return "The email already exists";
        }
        SUUser newUser = new SUUser();
        newUser.setSUID(suid);
        newUser.setName(name);
        newUser.setAddress(address);
        newUser.setEmail(email);
        newUser.setUserStatus(SUUserStatus.Idel);
        userrepo.save(newUser);
        return "User details were successfully saved";
    }
    private boolean isValidSuidLength(Integer suid) {
        return suid.toString().length() == 10;
    }

    public List<String> UserPickUpReq(Integer suid){
        List response = new ArrayList<>();
        SUUser user = userrepo.findBySUID(suid);

        if (userrepo.findSUUserBySUID(suid).isEmpty()) {
           response.add("Can you please register yourself before requesting a pickup");
        }
        else {
            SUUserStatus status = user.getUserStatus();
            int shuttlet;
            switch (status) {
                case Idel :
                    user.setUserStatus(SUUserStatus.Waiting);
                    userrepo.save(user);
                    shuttlet = generateRandomETA();
                    response.add("Your Waiting time for the ride is " + shuttlet + "minutes");
                    break;
                case Waiting :
                    shuttlet = generateRandomETA();
                    response.add("Your Waiting time for the ride is " + shuttlet + "minutes");
                    break;
                case Riding :
                    response.add("You are currently in a ride and cannot request a new ride untill this ride gets completed");
                    break;
            }
        }
        return response;
    }
    // Generate a random integer between 30 and 180 minutes for demonstration purposes
    private int generateRandomETA() {
        Random random = new Random();
        return random.nextInt(151) + 30;
    }
    public List<SUUser> Users() {
        return userrepo.findAll();
    }
    public String ShuttleUserEntry(Integer suid, String address) {
        SUUser user = userrepo.findBySUID(suid);

        if (user == null) {
            return "Enter a valid SUID";
        }

        if (user.getUserStatus() != SUUserStatus.Waiting) {
            return "User hasn't raised a request to PICK-UP";
        }

        SUShuttleLocation shuttle1 = shuttlerep.getReferenceById(1);
        SUShuttleLocation shuttle2 = shuttlerep.getReferenceById(2);

        int etaComparison = Integer.compare(ETAS1, ETAS2);

        if (etaComparison <= 0) {
            return addPassengerToShuttle(user, address, shuttle1, 1);
        } else {
            return addPassengerToShuttle(user, address, shuttle2, 2);
        }
    }

    private String addPassengerToShuttle(SUUser user, String address, SUShuttleLocation shuttle, int shuttleId) {
        if (shuttle.getSeats() == null || shuttle.getSeats() < 25) {
            SUShuttleUser passenger = new SUShuttleUser();
            passenger.setSuid(user.getSUID());
            passenger.setAddress(address);
            passenger.setShuttleid(shuttleId);

            user.setUserStatus(SUUserStatus.Riding);
            shuttleuser.save(passenger);
            userrepo.save(user);

            shuttle.setSeats((shuttle.getSeats() == null ? 0 : shuttle.getSeats()) + 1);
            return "Passenger added to Shuttle" + shuttleId;
        } else {
            return "No seats available in Shuttle" + shuttleId;
        }
    }

    public String ShuttleLocation(double latitude,double longitude,int shuttleid){
        SUShuttleLocation s;
        if (shuttleid==1){
            s=shuttlerep.getReferenceById(1);
            s.setLatitude(latitude);
            s.setLongitude(longitude);
            shuttlerep.save(s);
            return "Shuttle-1 Location has been updated";
        }
        else if(shuttleid==2){
            s=shuttlerep.getReferenceById(2);
            s.setLatitude(latitude);
            s.setLongitude(longitude);
            shuttlerep.save(s);
            return "Shuttle-2 Location has been updated";
        }
        else {
            return "invalid shuttle ID";
        }
    }

}


