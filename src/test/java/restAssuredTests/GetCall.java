package restAssuredTests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import restAssuredModels.Products;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class GetCall {

    private static final String baseURI = "http://localhost:8888/api_testing/";

    @Test
    public void getAllCategories() {

        var response =
                given().
                        when()
                        .get(baseURI + "category/read.php")
                        .then()
                        .statusCode(200);

        response.log().body();
    }

    @Test
    public void getProduct(int statusCode, String name) {


        var response =
                given()
                        .queryParam("id", 1003)
                        .when()
                        .get(baseURI + "product/read_one.php")
                        .then()
                        .statusCode(statusCode)
                        .assertThat()
                        .body("name", equalTo(name));

        response.log().body();

    }

    @Test
    public void getProduct() {


        var response =
                given()
                        .queryParam("id", "1024")
                        .when()
                        .get(baseURI + "product/read_one.php")
                        .then()
                        .statusCode(200)
                        .assertThat()
                        .body("name", equalTo("SweatBand"));

        response.log().body();

    }


    @Test
    public void getProducts() {
        var response = given()
                .when().get(baseURI + "product/read.php").
                then().statusCode(200)
                .assertThat().body("records.id", everyItem(notNullValue()))
                .body("records.name", hasItem("SweatBand"), "records.id", hasItem("1024"));
        response.log().body();
    }

    @Test
    public void getAllProducts() {
        given()
                .when()
                .get(baseURI + "product/read.php")
                .then()
                .statusCode(200)
                .log().all()
                .contentType(ContentType.JSON)
                .assertThat()
                .body("records.id", everyItem(greaterThan("0")))
                .body("records.name", everyItem(notNullValue()))
                .body("records.description", everyItem(notNullValue()))
                .body("records.category_id", everyItem(greaterThan("0")))
                .body("records.category_name", everyItem(notNullValue()))
                .body("records.price", everyItem(greaterThan("0")));

    }

    @Test
    public void deserialiseProduct() {

        Products expectedProduct = new Products(
                "1024",
                "SweatBand",
                "Red color sweatband of free size for gym",
                5.00,
                3,
                "Active Wear - Unisex"

        );

        Products actualProduct =
                given()
                        .queryParam("id", "1024")
                        .when()
                        .get(baseURI + "product/read_one.php").as(Products.class);

        assertThat("Products does not match", actualProduct, samePropertyValuesAs(expectedProduct));

    }

    @Test
    public void getVitaminProduct() {

        given()
                .queryParam("id", "18")
        .when()
                .get(baseURI + "product/read_one.php")
        .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .assertThat()
                    .body("id", equalTo("18"))
                    .body("name", equalTo("Multi-Vitamin (90 capsules)"))
                    .body("description", equalTo("A daily dose of our Multi-Vitamins fulfills a day’s nutritional needs for over 12 vitamins and minerals."))
                    .body("price", equalTo("10.00"))
                    .body("category_id", equalTo("4"))
                    .body("category_name", equalTo("Supplements"));
    }


}
