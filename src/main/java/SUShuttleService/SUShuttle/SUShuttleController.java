package SUShuttleService.SUShuttle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import SUShuttleService.SUShuttle.ShuttleService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Controller
public class SUShuttleController {
    private ShuttleService s;

    @PostMapping(path = "/ShuttleUserEntry")
    public @ResponseBody String ShuttleUserEntry (@RequestParam Integer SUID,@RequestParam String Address) {
        return s.ShuttleUserEntry(SUID,Address);
    }

    @PostMapping(path = "/Shuttlelocation")
    public @ResponseBody String ShuttleLocation (@RequestParam double Latitude,@RequestParam double Longitude,@RequestParam Integer Shuttleid) {
        return s.ShuttleLocation(Latitude,Longitude,Shuttleid);
    }

}
