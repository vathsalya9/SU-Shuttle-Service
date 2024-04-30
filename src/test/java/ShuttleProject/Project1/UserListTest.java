package ShuttleProject.Project1;

import ShuttleService.Models.Users;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserListTest {

    @Test
    public void testGetListOfUsers() {
        RestAssured.baseURI = "http://localhost:8080";

        List<Users> users = given()
                .when()
                .get("/Users")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", Users.class);

        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

}
