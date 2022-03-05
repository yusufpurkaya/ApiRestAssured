package get_http_request;

import base_url.RegresInBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest07  {
//extends RegresInBaseUrl
    /*
https://reqres.in/api/users URL request olustur.
body icerisindeki idsi 5 olan datayi
1) Matcher CLASS ile
2) JsonPath ile dogrulayin. q
*/

    @Test
    public void test07(){
     //   spec01.pathParams("parametre1","api","parametre2","users");

        //                              https://reqres.in      /   api      /  users
    //   Response response=given().spec(spec01).when().get("{/{parametre1}/{parametre2}");
String url="https://reqres.in/api/users";
        Response response=given().when().get(url);

        response.prettyPrint();
// matcher ile yap
       // response.then().assertThat().body("data[4].id", Matchers.equalTo(5),)


// jsonpathile yap
        JsonPath json=response.jsonPath();
        System.out.println(json.getList("data.email"));
        json.getString("data.first_name");
        json.getInt("data.id");

        Assert.assertEquals("charles.morris@reqres.in",json.getList("data[4].email"));
Assert.assertEquals(5,json.getInt("data[4].id"));
Assert.assertEquals("Charles",json.getString("data[4].first_name"));
Assert.assertEquals("Morris",json.getList("data[4].last_name"));
    }
}
