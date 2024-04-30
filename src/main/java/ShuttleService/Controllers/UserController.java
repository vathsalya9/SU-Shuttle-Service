package ShuttleService.Controllers;

import java.util.*;

import ShuttleService.Models.Users;
import ShuttleService.Services.ShuttleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private ShuttleService s;


    // API for the Registration of users
    @PostMapping(path = "/signIn")
    public @ResponseBody String UserEntry (@RequestParam Integer suid,@RequestParam String name,
                                           @RequestParam String email,@RequestParam String address) {
    return s.SignIn(suid,name,email,address);
    }


    // API for requesting pick up of users
    @PostMapping(path = "/UserPickUp")
    public @ResponseBody List<String> UserPickUpReq (@RequestParam Integer SUID) {
        return s.UserPickUpRequests(SUID);
    }


    // API to list all users
    @GetMapping (path = "/Users")
    public @ResponseBody List<Users> Users () {
        return s.Users();
    }

}
