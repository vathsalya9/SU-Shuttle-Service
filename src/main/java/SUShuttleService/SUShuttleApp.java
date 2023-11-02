package SUShuttleService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("SUShuttleService.SUBackEnd")
public class SUShuttleApp {
    public static void main(String[] args) {
        SpringApplication.run(SUShuttleApp.class, args);
    }
}
