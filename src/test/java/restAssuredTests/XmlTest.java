package restAssuredTests;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class XmlTest {

    @Test
    public void verifyStatusCode(){

        given().when()
                .get("https://stageappsp.smashfly.com/contactmanagerservice/v2/ContactManagerRestService.svc/help/operations/SaveContact")
                .then().statusCode(200);
    }


}
