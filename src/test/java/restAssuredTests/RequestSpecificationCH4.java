package restAssuredTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestSpecificationCH4 {
    private static RequestSpecification requestSpecification;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com/").build();

    }

    @Test
    public void requestAPostUsingRequestSpecification(){
        given()
                .spec(requestSpecification)
                .when().get("/posts").
                then().assertThat().statusCode(200);

    }

    private static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void createResponseSpecification(){

        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }

    @Test
    public void validateResponseUsingRequestSpecification(){
        given()
                .spec(requestSpecification)
                .when()
                .get("/posts/1").
                then()
                .spec(responseSpecification)
                .and()
                .assertThat()
                .body("userId", equalTo(1));

    }
}