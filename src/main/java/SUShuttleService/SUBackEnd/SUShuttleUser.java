package SUShuttleService.SUBackEnd;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "passengersData")
public class SUShuttleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer suid;
    private String address;

    private Integer shuttleid;

    public Integer getShuttleid() {
        return shuttleid;
    }

    public void setShuttleid(Integer shuttleid) {
        this.shuttleid = shuttleid;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSuid() {
        return suid;
    }

    public void setSuid(Integer suid) {
        this.suid = suid;
    }

}
