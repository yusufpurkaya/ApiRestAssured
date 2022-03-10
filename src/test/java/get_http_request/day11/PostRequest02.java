package get_http_request.day11;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest02 extends DummyBaseUrl {
    @Test
    public void test02(){
        // 4  Adim    URL,EXPECTED_DATA,RESPONSE_REQUEST,DOGRULAMA
        spec02.pathParams("bir","api","iki","v1","uc","create");
        // bu class icin 2 tane veriyi testda class indan alacaz
        // o sebepten 2 tane Hashmap olusturacaz biri giden icin biri gelen icin
        DummyTestData requestObj=new DummyTestData();
       // request data icin yani POST edecez
        HashMap<String,Object> requestBodyMap=requestObj.setUpRequestBody();
      // epected cunku gonderdigimizle gelen veriler farkli
        HashMap<String,Object> expectedDataMap=requestObj.setUpExpectedData();

        Response response=given().accept(ContentType.JSON).
                spec(spec02).
                body(requestBodyMap).
                when().
                post("/{bir}/{iki}/{uc}");
// to string JSONOBJECT de gerekli , Map,HashMap de gerek yok
response.then().log().all();

// Dogrulama
//

        response.then().statusCode(200).assertThat().body("status", equalTo(expectedDataMap.get("status")),
                "message",equalTo(expectedDataMap.get("message")));


// De-Seralization

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        Assert.assertEquals(actualDataMap.get("status"),expectedDataMap.get("status"));
Assert.assertEquals(actualDataMap.get("message"),expectedDataMap.get("message"));
Assert.assertEquals(response.statusCode(),expectedDataMap.get("StatusCode"));


// jsonPath
        JsonPath json=response.jsonPath();
        Assert.assertEquals(json.get("status"),expectedDataMap.get("status"));
Assert.assertEquals(json.get("message"),expectedDataMap.get("message"));
Assert.assertEquals(response.statusCode(),expectedDataMap.get("StatusCode"));


    }
   /*
http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ali Can",
    "salary":"2000",
    "age":"40",
}
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,

{
    "status": "success",
    "data": {
    "id":…
},
    "message": "Successfully! Record has been added."
}

olduğunu test edin
 */
}
