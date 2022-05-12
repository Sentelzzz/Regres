package org.example.tests;

import com.google.gson.Gson;
import org.example.objects.reqresObject.User;
import org.example.objects.reqresObject.UsersList;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeepTest {

    @Test
    public void listUsersTest() {
        String  body = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract().body().asString();

        UsersList usersList = new Gson().fromJson(body, UsersList.class);

        for (int i = 0; i < usersList.getData().size(); i++) {
            String firstName = usersList.getData().get(i).getFirstName();
            String lastName = usersList.getData().get(i).getLastName();
            System.out.println(firstName + " " + lastName);
        }
    }
}
