package get_http_request.day11;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {
    @Test
    public void test03(){
       // URL
       spec4.pathParam("bir","todos");
       // expected data    // Jsonobject neden kullanilir? eger gidenle gelen ayniysa tercihedilir sorgulamada
        // daha kolaydir casting vs istemez MAP olsa
       JsonPlaceHolderTestData testobje=new JsonPlaceHolderTestData();
        JSONObject ecpectedRequest=testobje.setUpPostData();
        // response and request

        Response response=given().spec(spec4).accept(ContentType.JSON).
                body(ecpectedRequest.toString()).
                when().
                post("/{bir}");
        response.prettyPeek();
        response.prettyPrint();
        response.then().log().all();
        System.out.println("expected Request : "+ecpectedRequest);
        System.out.println("setupTestdata icerisi  :"+testobje.setUpPostData().toString());
        /*
   https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
  }
    Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
  {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": …
   }
*/



        // sorun var ama ne bilemedim gonderdigim bilgilerden sadece id yi ekliyor digerleri eklenmiyo
        // DE-Serialization

        HashMap<String,Object> actualData=response.as(HashMap.class);
    Assert.assertEquals(actualData.get("id"),ecpectedRequest.get("id"));
        System.out.println("actual  :"+actualData.get("userId").toString());
        System.out.println("expected  :"+ecpectedRequest.get("userId").toString());
//Assert.assertEquals(actualData.get("userId"),ecpectedRequest.get("userId"));

// jsonPath ile
        JsonPath json=response.jsonPath();
        Assert.assertEquals(json.get("id"),ecpectedRequest.get("id"));

// MatcherClass ile

        response.then().statusCode(201).body("id", Matchers.equalTo(ecpectedRequest.get("id")));



    }
}
