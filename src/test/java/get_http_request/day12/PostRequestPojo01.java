package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;

public class PostRequestPojo01 extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos url ‘ine bir request gönderildiğinde
    Request body{
    "userId": 21,
    "id": 201,
    "title": "Tidy your room",
    "completed": false
    }
    Status kodun 201, response body ‘nin ise
    {
    "userId": 21,
    "id": 201,
    "title": "Tidy your room",
    "completed": false
    }
    */

    @Test
    public void test(){
        spec4.pathParams("bir","todos");

        // expected
        JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(21,201,"Tidy your room",false);

        // request ve response
        Response response=given().contentType(ContentType.JSON).spec(spec4).body(expectedData).when().post("/{bir}");
    response.prettyPeek();

    // ve dogrulama

        // De Serialization
        JsonPlaceHolderPojo actuelData=response.as(JsonPlaceHolderPojo.class);






    }
}
