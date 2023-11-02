package SUShuttleService.SUShuttle;

import java.util.*;

import SUShuttleService.SUBackEnd.SUUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import SUShuttleService.SUShuttle.ShuttleService;

@RestController

public class SUUserController {
    @Autowired
    private ShuttleService s;
    @PostMapping(path = "/UserEntry")
    public @ResponseBody String UserEntry (@RequestParam Integer SUID,@RequestParam String Name,
                                           @RequestParam String Email,@RequestParam String Address) {
    return s.UserEntry(SUID,Name,Email,Address);
    }

    @PostMapping(path = "/UserPickUp")
    public @ResponseBody List<String> UserPickUpReq (@RequestParam Integer SUID) {
        return s.UserPickUpReq(SUID);
    }

    @GetMapping (path = "/Users")
    public @ResponseBody List<SUUser> Users () {
        return s.Users();
    }

}
