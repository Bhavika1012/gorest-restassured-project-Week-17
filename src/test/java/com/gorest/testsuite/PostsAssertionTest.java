package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }


    //Verify the if the total record is 25
    @Test
    public void verifyTotalRecord() {
        response.body("size", equalTo(10));
    }

    //Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto
    //centum.”
    @Test
    public void verifyTitleOfSpecificId() {
        response.body("[2].title",equalTo("Adflicto denuo vomica deduco templum."));
    }



    //Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void checkMultipleIds() {
        response.body("id", hasItems(40105, 40098, 40097));
    }

    //Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”
    @Test
    public void verifyBodyOfId() {
        response.body("find{it.id == 39305}.body", equalTo("Adhuc crebro odit. Tres tredecim cubo. Adfectus universe crustulum. Thorax altus varius. Defigo utor succurro. Denique enim aliqua. Similique torqueo cogo. Succurro triginta thymum. Delectatio desolo atrox. Damno expedita accendo. Nemo cursim tenetur. Utor tamen qui. Ambitus quos baiulus. Sollicito vicissitudo cras. Voluptas numquam sperno. Cultellus auris curriculum. Cogito sodalitas quia. Adaugeo blandior amplus. Tolero libero capio."));
    }

}
