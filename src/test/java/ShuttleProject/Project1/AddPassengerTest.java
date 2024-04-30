package ShuttleProject.Project1;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class AddPassengerTest {

    @Test
    public void testBusBackAtCampus() {
        RestAssured.baseURI = "http://localhost:8080";

        given()
                .when()
                .post("/ShuttleUserEntry")
                .then()
                .statusCode(200)
                .body(equalTo("The bus is at campus"));
    }

}