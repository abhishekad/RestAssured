package restAssuredTests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PutCall {

    private static final String baseURI = "http://localhost:8888/api_testing/product/update.php";

    @Test
    public void updateProduct(){
        String body = " {\n" +
                "    \"id\": \"1004\",\n" +
                "    \"name\": \"Water Bottle\",\n" +
                "    \"description\": \"This is organic water bottle made using bamboo and can store 1 litre of water.\",\n" +
                "    \"price\": \"350.00\",\n" +
                "    \"category_id\": \"3\",\n" +
                "    \"category_name\": \"Active Wear - Unisex\"\n" +
                "}";

        var response =
                given().
                        body(body)
                .when()
                        .put(baseURI)
                .then()
                        .statusCode(200);

        response.log().body();


    }
}
