package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static String name = "John" + TestUtils.getRandomValue();
    static String email = "johnsmith" + TestUtils.getRandomValue() + "@gmail.com";
    static String updatedEmail = "johnsmith123" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userId;

    static   String token = "d88d02069e48aedd8e5032e17f21b929e78721f871e7289b0dc925c3571ee4f7";


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //  RestAssured.port = 3030;
    }

    @Test()
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json","Authorization", "Bearer "+token)
                .when()
                .body(userPojo)
                .post("/public/v2/users");
        response.then().log().all().statusCode(201);

        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        userId = jsonPath.getInt("id");
    }
    @Test
    public void verifyUserUpdateSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(updatedEmail);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json","Authorization", "Bearer "+token)
                .when()
                .body(userPojo)
                .patch("/public/v2/users/" + userId);
        response.then().log().all().statusCode(200);

    }

    @Test
    public void verifyUserDeleteSuccessfully() {
        Response response = given()

                .header("Content-Type","application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")

                .when()
                .delete("/users/5315");
        response.then().statusCode(404);
        response.prettyPrint();
    }
}
