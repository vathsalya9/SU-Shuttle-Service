package SUShuttleService.SUBackEnd;

import javax.persistence.*;

@Entity
@Table (name="shuttleloc")
public class SUShuttleLocation {
    @Id
    private Integer id;
    private Integer sid;
    private double latitude;
    private double longitude;
    private Integer Seats;

    public Integer getSeats() {
        return Seats;
    }

    public void setSeats(Integer seats) {
        Seats = seats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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
