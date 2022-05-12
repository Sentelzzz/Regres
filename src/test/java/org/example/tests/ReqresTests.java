package org.example.tests;

import org.example.objects.User;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReqresTests {


    //https://reqres.in/

    @Test
    public void singleUserTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void singleUserNotFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    public void listResourceTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200);
    }

    @Test
    public void singleResourceNotFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404);
    }

    @Test
    public void createTest() {
        User user = User.builder()
                .name("morpheus")
                .job("leader")
                .build();
        given()
                .body(user)
                .log().all()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);
    }

    @Test
    public void updatePutTest() {
        given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void updatePatchTest() {
        given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteTest() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    public void registerSuccessfulTest() {
        User user = User.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();
        given()
                .body(user)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void registerUnsuccessfulTest() {
        given()
                .body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400);
    }

    @Test
    public void loginSuccessfulTest() {
        User user = User.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        given()
                .body(user)
                .log().all()
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void loginUnsuccessfulTest() {
        given()
                .body("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400);
    }

    @Test
    public void delayedResponseTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200);
    }
}
