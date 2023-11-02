package SUShuttleService.SUBackEnd;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class SUUser {
    @Id
    private Integer SUID;
    private String name;
    private String email;
    private String address;
    private SUUserStatus userStatus;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSUID() {
        return SUID;
    }

    public void setSUID(Integer SUID) {
        this.SUID = SUID;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public SUUserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(SUUserStatus userStatus) {
        this.userStatus = userStatus;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
