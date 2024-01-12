package restAssuredTests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class ParameterisationCH3 {

    // We can parameterize the rest assured test cases using the data provider mechanism

    // create a data provider and enter the test data into the data provider
    // now run rest assured test case with the help of the data provider

    @DataProvider
    public static Object[][] postsAndPostId(){

        return new Object[][]{
                {"posts", "1", "{\n" +
                        "  \"userId\": 1,\n" +
                        "  \"id\": 1,\n" +
                        "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                        "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                        "}"},
                {"posts", "9","{\n" +
                        "  \"userId\": 1,\n" +
                        "  \"id\": 7,\n" +
                        "  \"title\": \"magnam facilis autem\",\n" +
                        "  \"body\": \"dolore placeat quibusdam ea quo vitae\\nmagni quis enim qui quis quo nemo aut saepe\\nquidem repellat excepturi ut quia\\nsunt ut sequi eos ea sed quas\"\n" +
                        "}" },
                {"posts", "9","{\n" +
                        "  \"userId\": 1,\n" +
                        "  \"id\": 9,\n" +
                        "  \"title\": \"nesciunt iure omnis dolorem tempora et accusantium\",\n" +
                        "  \"body\": \"consectetur animi nesciunt iure dolore\\nenim quia ad\\nveniam autem ut quam aut nobis\\net est aut quod aut provident voluptas autem voluptas\"\n" +
                        "}"}

        };

    }

    @Test
    @UseDataProvider("postsAndPostId")
    public void runTestWithParam(String type, String postId, String expectedResponseBody){

        given().pathParams("type", type).pathParams("postId", postId)
                .when().get("https://jsonplaceholder.typicode.com/{type}/{postId}")
                .then().body(equalTo(expectedResponseBody));

    }
}
