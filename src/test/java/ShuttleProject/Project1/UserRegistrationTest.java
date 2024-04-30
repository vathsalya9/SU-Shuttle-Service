package ShuttleProject.Project1;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class UserRegistrationTest {

    @Test
    public void testValidUserRegistration() {
        RestAssured.baseURI = "http://localhost:8080";

        // Valid user parameters
        String name = "Bob";
        String address = "732 Westcott St";
        String email = "bob@syr.edu";
        int suid = 123456781;

        given()
                .param("name", name)
                .param("address", address)
                .param("suid", suid)
                .param("email",email)
                .log().all()
                .when()
                .post("/signIn")
                .then()
                .log().all()
                .statusCode(200)
                .body(equalTo("User details were successfully saved"));
    }

    @Test
    public void testExistingUserRegistration() {
        RestAssured.baseURI = "http://localhost:8080";

        // Valid user parameters
        String name = "Alice Smith";
        String address = "456 Elm St";
        String email = "alice@syr.edu";
        int suid = 123456780; //existing SUID

        given()
                .param("name", name)
                .param("address", address)
                .param("suid", suid)
                .param("email",email)
                .log().all()
                .when()
                .post("/signIn")
                .then()
                .log().all()
                .statusCode(200)
                .body(equalTo("The SUID already exists"));
    }

    @Test
    public void testExistingUserEmailRegistration() {
        RestAssured.baseURI = "http://localhost:8080";

        // Valid user parameters
        String name = "Alice Smith";
        String address = "456 Elm St";
        String email = "alice@syr.edu"; //existing email
        int suid = 123456787;

        given()
                .param("name", name)
                .param("address", address)
                .param("suid", suid)
                .param("email",email)
                .log().all()
                .when()
                .post("/signIn")
                .then()
                .log().all()
                .statusCode(200)
                .body(equalTo("The email already exists"));
    }

    @Test
    public void testInvalidSUIDUserRegistration() {
        RestAssured.baseURI = "http://localhost:8080";

        // Invalid SUID (too short)
        String name = "Bob Johnson";
        String address = "789 Oak St";
        String email = "bob@syr.edu";
        int suid = 123; // Invalid SUID

        given()
                .param("name", name)
                .param("address", address)
                .param("suid", suid)
                .param("email",email)
                .log().all()
                .when()
                .post("/signIn")
                .then()
                .log().all()
                .statusCode(200)
                .body(equalTo("Please enter a valid 9-digit SUID"));
    }

    @Test
    public void testEmptyNameUserRegistration() {
        RestAssured.baseURI = "http://localhost:8080";

        String name = ""; // Empty name
        String address = "111 Pine St";
        String email = "jj@syr.edu";
        int suid = 123;

        given()
                .param("name", name)
                .param("address", address)
                .param("suid", suid)
                .param("email",email)
                .log().all()
                .when()
                .post("/signIn")
                .then()
                .log().all()
                .statusCode(200);
    }

}
