package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderBaseUrl {
      /*
 https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
 {
  "title": "Batch44"
 }
 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "userId": 10,
 "title": "Batch44"
 "completed": true,
 "id": 198
 }
 */
@Test
    public void test01(){
    spec4.pathParams("bir","todo","iki","198");
    JsonPlaceHolderTestData obj2=new JsonPlaceHolderTestData();
    JSONObject expectedData=obj2.setUpPatchExpectedData();  // cevapla kiyaslama icin
    JSONObject requestData=obj2.setUpPatchRequestData();   // gidecek
    given().contentType(ContentType.JSON).spec(spec4).body(requestData.toString()).when().post("/{bir}/{iki}");

    Response response=given().contentType(ContentType.JSON).spec(spec4).body(requestData.toString()).when().patch("/{bir}/{iki}");
response.prettyPeek();
    System.out.println(expectedData);
    //{"completed":false,"id":198,"title":"Batch44","userId":10}

    System.out.println(expectedData);
    //{"completed":false,"id":198,"title":"Batch44","userId":10}

    response.prettyPrint();
// ve 3 farkli yolla Dogrulama yap

    // Matcher

    // jsonPath

    //DeSerialization

}







}
