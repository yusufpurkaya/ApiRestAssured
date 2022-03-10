package get_http_request.day08;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest20 extends JsonPlaceHolderBaseUrl {
    @Test
    public void test20(){

        //https://jsonplaceholder.typicode.com/todos/2
        spec4.pathParams("bir","todos","iki",2);
        Response response=given().spec(spec4).when().get("/{bir}/{iki}");
        response.prettyPeek();
        response.prettyPrint();


       Map<String,Object> expectedData=new HashMap<>();
       expectedData.put("StatusCode",200);
        expectedData.put("completed",false);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("userId",1);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
   //1) Status kodunun 200,
        //2) respose body'de,
        //         "completed": değerinin false
        //         "title": değerinin "quis ut nam facilis et officia qui"
        //         "userId" sinin 1 ve
        //    header değerlerinden
        //         "via" değerinin "1.1 vegur" ve
        //         "Server" değerinin "cloudflare" olduğunu test edin…
Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("actuel data ************ \n"+actualData.toString());
        //actuel data ************
        //{id=2, completed=false, title=quis ut nam facilis et officia qui, userId=1}
        // yani actualdata Map aslinda body i getirdi

        System.out.println("statuc code *********----------"+response.statusCode());
// actual ile expected i karsilastir    3    yol ile

       // Matcher ile
        response.then().assertThat().statusCode((Integer) expectedData.get("StatusCode")).
               headers("Via",Matchers.equalTo(expectedData.get("Via")),
                       "Server",equalTo(expectedData.get("Server")))
                .body("title",equalTo(expectedData.get("title")),
                        "completed",equalTo(expectedData.get("completed")),
                        "userId",equalTo(expectedData.get("userId")));

        // json ile
        JsonPath json=response.jsonPath();
        System.out.println("jason  ++++++++++ \n"+json.prettyPrint());
        System.out.println("=== \n"+json.peek());
        // onemli nokta su header dan mi geliyo body den mi ?
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("Via"),actualData.get("Via"));
        Assert.assertEquals(expectedData.get("Server"),actualData.get("Server"));


    }
}
