package restAssuredTests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class Chapter1 {

    @Test
    public void testTheAPIResponseForBaverlyHills(){

        when().get("https://jsonplaceholder.typicode.com/posts").
                then().statusCode(200).body("[0].userId", equalTo(1));
    }

    @Test
    public void assertContentTypeOfTheAPI(){
        when().get("https://jsonplaceholder.typicode.com/posts").
                then().statusCode(200).contentType(ContentType.JSON);

//    OR            .contentType("application/json");
    }

    @Test
    public void logRequestAndResponseOfAPI(){

        given().log().all().
                when().get("https://jsonplaceholder.typicode.com/posts")
                .then().log().body();
    }

    @Test
    public void assertPostTitle(){

        given().when().get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .body("[4].title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }


}
