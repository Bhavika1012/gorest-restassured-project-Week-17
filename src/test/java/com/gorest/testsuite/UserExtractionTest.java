package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {
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

    @Test
    //Extract the All Ids
    public void extractAllIds() {

        List<Integer> idList = response.extract().path("id");
        System.out.println("List of ID's: " + idList);
    }

    @Test
    //2. Extract the all Names
    public void getAllNames() {
        List<String> nameList = response.extract().path("name");
        System.out.println("List of names: " + nameList);
    }


    @Test
    //Extract the name of 5th object
    public void extractSpecificName() {
        String name = response.extract().path("[4].name");
        System.out.println("The 5th name on the list is: " + name);
    }

    @Test
    //Extract the names of all object whose status = inactive
    public void extractInactiveStatus() {
        List<HashMap<String, ?>> inactive = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("Names of all object whose status = inactive: " + inactive);
    }

    @Test
    //Extract the gender of all the object whose status = active
    public void extractGenderActiveStatus() {
        List<HashMap<String, ?>> active = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("Gender of all object whose status = active: " + active);
    }

    @Test
    //Print the names of the object whose gender = female

    public void genderFemale() {
        List<HashMap<String, ?>> gender = response.extract().path("findAll{it.gender=='female'}.name");
    }

    @Test
    //7. Get all the emails of the object where status = inactive
    public void emailStatusInactive() {
        List<HashMap<String, ?>> email = response.extract().path("findAll{it.status=='inactive'}.email");
    }

    @Test
    //8. Get the ids of the object where gender = male
    public void genderMale() {
        List<HashMap<String, ?>> genderMale = response.extract().path("findAll{it.gender=='female'}.id");
        System.out.println(" The IDs of the object where gender = male" + genderMale);
    }

    @Test
    //9. Get all the status
    public void getAllStatus() {

        List<String> allStatus = response.extract().path("status");
        System.out.println("All the status are : " + allStatus);
    }

    @Test
    //10. Get email of the object where name = Karthik Dubashi IV
    public void GetEmailOf() {

        List<String> email = response.extract().path("findAll{it.name=='Laal Kaul'}.email");
        System.out.println("The email of the object where name = Laal Kaul" + email.get(0));
    }

    @Test
    //11. Get gender of id = 5471
    public void getGenderOfId() {
        List<String> genderID = response.extract().path("findAll{it.id==5313}.gender");
        System.out.println("Get gender of id = 5313 :" + genderID.get(0));
    }

}
