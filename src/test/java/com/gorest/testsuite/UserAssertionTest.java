package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIT() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

    // 1. Verify the if the total record is 20
    @Test
    public void verifyTotalRecord() {
        response.body("size", equalTo(20));
    }

    //Verify if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void verifyNameOfId() {
        response.body("[0].name", equalTo("Chandraswaroopa Joshi"));
    }

    @Test
    //Check the single ‘Name’ in the Array list (Subhashini Talwar)
    public void singleName() {
        response.body("name", hasItem("Chandraswaroopa Joshi"));
    }

    @Test
    //Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    public void checkMultipleNames() {
        response.body("name", hasItems("Mrs. Agrata Panicker", "Bala Patel", "Mrs. Mandaakin Kaul"));
    }

    @Test
    //Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    public void verifyEmailWithUserId() {
        response.body("findAll{it.id==5471}.email", equalTo("adiga_aanjaneya_rep@jast.org"));
    }

    @Test
    //Verify the status is “Active” of user name is “Shanti Bhat V”
    public void verifyStatusOfUser() {
        response.body("findAll{it.name== 'Rameshwar Guha Sr.'}.status", hasItem("active"));
    }

    @Test
    //Verify the Gender = male of user name is “Niro Prajapat”
    public void verifyGenderMale() {
        response.body("[0].gender", equalTo("female"));
        response.body("[0].name", equalTo("Chandraswaroopa Joshi"));

    }

}
