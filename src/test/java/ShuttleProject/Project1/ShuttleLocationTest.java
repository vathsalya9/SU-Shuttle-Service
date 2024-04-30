package ShuttleProject.Project1;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ShuttleLocationTest {

    @Test
    public void testShuttleLocation() {
        RestAssured.baseURI = "http://localhost:8080";

        double latitude = 43.0481;
        double longitude = -76.1474;
        int shuttleId = 1;

        given()
                .param("Latitude", latitude)
                .param("Longitude", longitude)
                .param("Shuttleid", shuttleId)
                .when()
                .post("/ShuttleLocation")
                .then()
                .statusCode(200)
                .body(equalTo("Shuttle Location has been updated"));
    }



}