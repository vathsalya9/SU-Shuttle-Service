package ShuttleProject.Project1;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class UserRequestPickupTest {

    @Test
    public void testValidSUIDRequestPickupWaiting() {
        RestAssured.baseURI = "http://localhost:8080";

        int suid = 123456781; // Assuming a valid SUID for a user with status NOT_REQUESTED

        String responseBody = given()
                .param("SUID", suid)
                .log().all()
                .when()
                .post("/UserPickUp")
                .then()
                .log().all()
                .statusCode(200)
                .extract().asString();

        assertThat(responseBody, containsString("Your Waiting time for the ride is 15 minutes"));
    }

    @Test
    public void testUserRequestDuringRide() {
        RestAssured.baseURI = "http://localhost:8080";

        int suid = 123456780; // Assuming a valid SUID for a user with status ENROUTE

        String response = given()
                .param("SUID", suid)
                .log().all()
                .when()
                .post("/UserPickUp")
                .then()
                .log().all()
                .statusCode(200)
                .extract().asString();

        assertThat(response, containsString("You are currently in a ride and cannot request a new ride"));
    }

    @Test
    public void testInvalidSUIDRequestPickup() {
        RestAssured.baseURI = "http://localhost:8080";

        int suid = 123; // Invalid SUID

        String responseBody = given()
                .param("SUID", suid)
                .log().all()
                .when()
                .post("/UserPickUp")
                .then()
                .log().all()
                .statusCode(200)
                .extract().asString();
        assertThat(responseBody, containsString("Can you please register yourself before requesting a pickup"));
    }
}