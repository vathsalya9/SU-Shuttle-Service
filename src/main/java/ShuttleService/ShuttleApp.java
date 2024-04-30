package ShuttleService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("ShuttleService.Models")
public class ShuttleApp {
    public static void main(String[] args) {
        SpringApplication.run(ShuttleApp.class, args);
    }
}
