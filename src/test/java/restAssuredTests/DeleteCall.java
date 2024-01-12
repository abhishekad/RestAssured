package restAssuredTests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteCall {

    private static final String baseURI = "http://localhost:8888/api_testing/product/delete.php";

    @Test
    public void deleteProduct(){
        String body = "{\n" +
                "    \"id\": \"1004\"\n" +
                "}";

        var response =
                given().
                        body(body)
                .when()
                        .delete(baseURI)
                .then();

        response.log().body();
        GetCall gc = new GetCall();
        gc.getProduct(404, null);
    }
}
