package restAssuredTests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import restAssuredModels.Products;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)



public class PostCall {

    private static final String baseURI = "http://localhost:8888/api_testing/";


    // Test without serialisation (passing body directly into the method)
    @Test
    public void createProduct(){

        String body = "{\n" +
                "    \"id\": \"99\",\n" +
                "    \"name\": \"Water Bottle\",\n" +
                "    \"description\": \"This is organic water bottle made using bamboo.\",\n" +
                "    \"price\": \"300.00\",\n" +
                "    \"category_id\": \"3\",\n" +
                "    \"category_name\": \"Active Wear - Unisex\"\n" +
                "}";

        var response =
                given().
                        body(body)
                .when()
                        .post(baseURI + "product/create.php")
                .then()
                        .statusCode(201)
                        .assertThat()
                        .body("message", equalTo("Product was created."));

        response.log().body();

    }

    // Test using the serialisation concept
    @Test
    public void createSerialisedProduct(){

        Products product = new Products("White Water Bottle",
                "Made of oak and can store 1 litre of water",
                12.00,
                3);

        var response =
                given()
                        .body(product)
                .when()
                        .post(baseURI + "product/create.php")
                .then()
                        .statusCode(201)
                        .assertThat()
                        .body("message", equalTo("Product was created."));

        response.log().body();


    }
}
