package ShuttleService.Models;

import javax.persistence.*;

@Entity
@Table(name = "ShuttleRiders")
public class ShuttleUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer SUID;
    private String address;

    private Integer shuttleID;

    public Integer getShuttleID() {
        return shuttleID;
    }

    public void setShuttleID(Integer shuttleID) {
        this.shuttleID = shuttleID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSUID() {
        return SUID;
    }

    public void setSUID(Integer SUID) {
        this.SUID = SUID;
    }

}
