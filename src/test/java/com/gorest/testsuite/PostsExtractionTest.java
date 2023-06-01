package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //Extract the title
    @Test
    public void extractTitle() {
        List<String> title = response.extract().path("title");
        System.out.println("The title is : " + title);
    }

    // Extract the total number of record
    @Test
    public void totalNumber() {
        List<String> total = response.extract().path("title");
        int numberOfRecords = total.size();
        System.out.println("The value of title is : " + numberOfRecords);
    }

    // Extract the body of 15th record
    @Test
    public void extract15thRecord() {
        String body = response.extract().path("[14].body");
        System.out.println("The body of 15th record is : " + body);
    }

    // Extract the user_id of all the records
    @Test
    public void extractUserId() {
        List<String> userId = response.extract().path("user_id");
        System.out.println("The user Id of all the records are : " + userId);
    }

    // Extract the title of all the records
    @Test
    public void extractTitleOfAllRecords() {
        List<String> title = response.extract().path("title");
        System.out.println("The title of all the records are : " + title);
    }

    // Extract the title of all records whose user_id = 39655
    @Test
    public void extractTitleOfSpecificRecord() {
        List<String> title = response.extract().path("findAll{it.user_id = 39655}.title");
        System.out.println("The title of all records whose user Id = 39655 are : " + title);
    }

    // Extract the body of all records whose id = 39654
    @Test
    public void extractBodyOfSpecificRecord() {
        List<String> body = response.extract().path("findAll{it.id = '39654'}.body");
        System.out.println("The title of body of all records whose id = 39654 are : " + body);
    }
}
