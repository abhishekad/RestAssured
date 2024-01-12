package restAssuredTests;

import org.junit.jupiter.api.*;
import restAssuredModels.Products;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndToEndTest {

    // end to end test
    // Create product
    // update the product
    // get the product details
    // delete the product

    private static final String baseURI = "http://localhost:8888/api_testing/";
    public static String productID = "1010";

    @Test
    @Order(1)
    public void createNewProduct(){
        Products prod = new Products("SweatBand",
            "Red color sweatband of free size for gym",
            5.00,
            3);

        var response =
                given()
                        .body(prod)
                .when()
                        .post(baseURI + "product/create.php")
                .then()
                        .statusCode(201)
                        .assertThat()
                        .body("message", equalTo("Product was created."));

        response.log().body();
    }

    @Test
    @Order(2)
    public void updateProduct(){
        Products prod = new Products("1010",
                "SweatBand",
                "Red color sweatband of free size for gym",
                6.00,
                3);

        var response =
                given()
                        .body(prod)
                        .when()
                        .post(baseURI + "product/update.php")
                        .then()
                        .statusCode(200)
                        .assertThat()
                        .body("message", equalTo("Product updated"));

        response.log().body();

    }

    @Test
    @Order(3)
    public void getTheProductDetails(){
        var response =
                given()
                        .queryParam("id", productID).
                when()
                        .get(baseURI + "product/read_one.php")
                .then()
                        .statusCode(200)
                        .assertThat()
                        .body("name", equalTo("SweatBand"))
                        .body("description", equalTo("Red color sweatband of free size for gym"))
                        .body("category_id", equalTo("3"));;

        response.log().body();
    }

    @Test
    @Order(4)
    public void deleteSweatBand(){
        String body = "{\n" +
                "    \"id\": \"1009\"\n" +
                "}";
        var response =
                given().
                        body(body)
                .when()
                        .delete(baseURI + "product/delete.php").
                then().
                        statusCode(200)
                        .assertThat().body("message", equalTo("Product was deleted."));
    }
}
