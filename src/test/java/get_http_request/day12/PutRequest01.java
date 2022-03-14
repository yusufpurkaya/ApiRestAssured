package get_http_request.day12;

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
import static org.hamcrest.Matchers.equalTo;

public class PutRequest01 extends JsonPlaceHolderBaseUrl {
     /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body'İ PUT ettiğinizde
    {
    "userId": 21,
    "title": "Wash the dishes",
    "completed": false
    }
    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    {
    "userId": 21,
    "title": "Wash the dishes",
    "completed": false,
    "id": 198
    }
    */

    @Test
    public void test01(){
        spec4.pathParams("bir","todos","iki","198");
        // expected  TestData da Json object kullanarak bu expected i olusturduk
        // burada o class dan obje işle Json objesine atayacagiz
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        JSONObject expectedRequest=obj.setUpPutData();




        Response response=given().contentType(ContentType.JSON).
                spec(spec4).
                body(expectedRequest.toString())
                .when().put("/{bir}/{iki}");
        response.prettyPeek();
//     "userId": 21,
//    "title": "Wash the dishes",
//    "completed": false,
//    "id": 198
        System.out.println(expectedRequest.toString()+"--");
    //    {"completed":false,"title":"Wash the dishes","userId":21}

        response.then().assertThat().body("completed", equalTo(expectedRequest.get("completed")),
                "id",equalTo(198),
                "title",equalTo(expectedRequest.get("title")),
                "userId",equalTo(21));
        JsonPath json=response.jsonPath();
        System.out.println(json.toString()+"=json");
        Assert.assertEquals(expectedRequest.getString("title"),json.getString("title"));
Assert.assertEquals(expectedRequest.getInt("userId"),json.getInt("userId"));
Assert.assertEquals(expectedRequest.getBoolean("completed"),json.getBoolean("completed"));
   // Assert.assertEquals(expectedRequest.getInt("id"),json.get("id"));

    // DeSerialization
        HashMap<String,Object> actualData=response.as(HashMap.class);
        //Assert.assertEquals(actualData.get("id"),expectedRequest.getInt("id"));
    Assert.assertEquals(actualData.get("title"),expectedRequest.get("title"));
    Assert.assertEquals(actualData.get("completed"),expectedRequest.getBoolean("completed"));
    Assert.assertEquals(actualData.get("userId"),expectedRequest.get("UserId"));

    }

}
