package ShuttleService.Services;

import java.util.*;
import ShuttleService.Repositories.*;
import ShuttleService.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShuttleService {
    @Autowired
    private UserRep users;
    @Autowired
    private ShuttleUserRep shuttleusers;
    @Autowired
    private ShuttleRep shuttlelocation;
    private double CurrentLatitude = 0.0 ;
    private double CurrentLongitude = 0.0 ;


    private static Queue<Users> waitingQueue = new LinkedList<>(); // we use a waiting Queue to add passengers and see if seats are available


    // user registration method
    public String SignIn(Integer suid, String name, String email, String address) {
        if (!isValidSuidLength(suid)) {
            return "Please enter a valid 9-digit SUID";
        }
        if (!users.findUserBySUID(suid).isEmpty() ) {
            return "The SUID already exists";
        }
        if (!email.endsWith("syr.edu")) {
            return "Please enter a valid SU email";
        }
        if (users.findByEmail(email) != null) {
            return "The email already exists";
        }
        Users newUser = new Users();
        newUser.setSUID(suid);
        newUser.setName(name);
        newUser.setAddress(address);
        newUser.setEmail(email);
        newUser.setUserStatus(UserStatus.NOT_REQUESTED);
        users.save(newUser);
        return "User details were successfully saved";
    }
    private boolean isValidSuidLength(Integer SUID) {
        return SUID.toString().length() == 9;
    }


    // user wants to be picked up
    public List<String> UserPickUpRequests(Integer SUID){
        List<String> response = new ArrayList<>();
        Users user = users.findBySUID(SUID);

        if (users.findUserBySUID(SUID).isEmpty()) {
            response.add("Can you please register yourself before requesting a pickup");
        }
        else {
            UserStatus status = user.getUserStatus();
            int waitTime;
            switch (status) {
                case NOT_REQUESTED:
                    user.setUserStatus(UserStatus.REQUESTED);
                    waitingQueue.add(user);
                    waitTime = generateWaitingTime(waitingQueue.size());
                    user.setWaitingTime(waitTime);
                    users.save(user);
                    response.add("Your Waiting time for the ride is " + waitTime + " minutes");
                    break;
                case REQUESTED:
                    waitTime = user.getWaitingTime();
                    response.add("Your Waiting time for the ride is " + waitTime + " minutes");
                    break;
                case ENROUTE:
                    response.add("You are currently in a ride and cannot request a new ride until this ride gets completed");
                    break;
            }
        }
        System.out.println(waitingQueue);
        return response;
    }

    private int generateWaitingTime(int positionInQueue) {
        int waitTime = 15;
        return waitTime * positionInQueue;  // For example, increase by 15 minutes for each position in the queue
    }

    public List<Users> Users() {
        return users.findAll();
    }


    // method to update the shuttle location
    public String ShuttleLocation(double Latitude,double Longitude,int ShuttleID){
        ShuttleLocation s = new ShuttleLocation();
        s.setLatitude(Latitude);
        s.setLongitude(Longitude);
        s.setSeats(30);
        s.setShuttleID(ShuttleID);
        shuttlelocation.save(s);
        return "Shuttle Location has been updated";
    }



    // method to add passengers to the shuttle with a maximum seating capacity of 30
    public String AddShuttleUser() {
        ShuttleLocation shuttle = new ShuttleLocation();
        System.out.println(waitingQueue);
        int maxCapacity = 30; // Assuming 30 is the maximum capacity for demonstration.
        int currentCapacity = shuttle.getSeats(); // Get the current seat count.

        while (!waitingQueue.isEmpty() && currentCapacity > 0) {
            Users user = waitingQueue.poll();
            CurrentLatitude += 10;
            CurrentLongitude += 20;
            ShuttleUsers shuttleUser = new ShuttleUsers();
            shuttleUser.setSUID(user.getSUID());
            shuttleUser.setAddress(user.getAddress());
            shuttleUser.setShuttleID(1);
            user.setUserStatus(UserStatus.ENROUTE);
            user.setWaitingTime(null);
            shuttleusers.save(shuttleUser);
            users.save(user);

            currentCapacity--; // Decrement the seat count.
            shuttle.setSeats(currentCapacity); // Update the shuttle's seat count.

            System.out.println("Capacity of the bus: " + maxCapacity);
            System.out.println("Seats remaining: " + currentCapacity);
            System.out.println("Shuttle's Latitude: "+CurrentLatitude + " and Longitude: " + CurrentLongitude);

            ShuttleLocation(CurrentLatitude, CurrentLongitude,1); // Update shuttle location, removing the '1' since it doesn't match the method signature you provided earlier.

            if (currentCapacity <= 0) {
                System.out.println("Bus is full, wait for the next bus");
                break; // Only break if we have actually reached capacity.
            }
        }

        // If the loop ends because the queue is empty, you can handle it here.
        if (waitingQueue.isEmpty()) {
            System.out.println("All waiting users have been processed.");
        }


        //Bus back at campus
        ShuttleLocation(0,0, 1);
        return "The bus is at campus";
        }


    }


