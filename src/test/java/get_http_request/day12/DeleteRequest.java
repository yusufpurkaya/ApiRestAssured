package get_http_request.day12;

import base_url.DummyBaseUrl;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import test_data.DummyTestData;

import static io.restassured.RestAssured.given;

public class DeleteRequest extends DummyBaseUrl {
     /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    {
    "status": "success",
    "data": "2",
    "message": "Successfully! Record has been deleted"
    }
   */
    @Test
    public void test(){
        spec02.pathParams("bir","api","iki","v1","uc","delete","dort",2);

        DummyTestData obj=new DummyTestData();
        JSONObject expectedData=obj.setUpDeleteExpectedData();
        Response response=given().spec(spec02).when().delete("/{bir}/{iki}/{uc}/{dort}");
        response.prettyPeek();
    }
}
