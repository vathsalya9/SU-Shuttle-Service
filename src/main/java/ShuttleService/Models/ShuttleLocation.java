package ShuttleService.Models;

import javax.persistence.*;

@Entity
@Table (name="ShuttleLocation")
public class ShuttleLocation {
    @Id
    private Integer shuttleID;
    private double latitude;
    private double longitude;
    private static Integer seats;

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        ShuttleLocation.seats = seats;
    }


    public Integer getShuttleID() {
        return shuttleID;
    }

    public void setShuttleID(Integer shuttleID) {
        this.shuttleID = shuttleID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
