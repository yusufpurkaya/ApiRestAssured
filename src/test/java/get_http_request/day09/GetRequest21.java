package get_http_request.day09;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class GetRequest21 extends JsonPlaceHolderBaseUrl {
    @Test
    public void test21(){

        //        https://jsonplaceholder.typicode.com/todos/2

        spec4.pathParams("bir","todos","iki",2) ;
        Response response=given().spec(spec4).when().get("/{bir}/{iki}");

        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
       HashMap<String,Object> expectedData= (HashMap<String, Object>) obj.setUpTestData();
// test_Data icindeki methodu static yapinca obje uretmeden class adiyla ulasabiliyorum
        HashMap<String,Object> expectedDataStaticMethodla= (HashMap<String, Object>) JsonPlaceHolderTestData.setUpTestDatastatic();
        System.out.println(expectedData);


response.prettyPrint();
response.prettyPeek();
        //        1) Status kodunun 200,
        //        2) respose body'de,
        //         "completed": değerinin false
        //         "title": değerinin "quis ut nam facilis et officia qui"
        //         "userId" sinin 1 ve
        //        header değerlerinden
        //         "via" değerinin "1.1 vegur" ve
        //         "Server" değerinin "cloudflare" olduğunu test edin…


        //Server=cloudflare, completed=false, title=quis ut nam facilis et officia qui, StatusCode=200, userId=1, Via=1.1 vegur
        response.then().assertThat().statusCode((Integer)expectedData.get("StatusCode")).
                body("completed", equalTo(expectedData.get("completed")),
                        "title",equalTo(expectedData.get("title")),
                        "userId",equalTo(expectedData.get("userId"))).headers("Via",equalTo(expectedData.get("Via")),"Server",expectedData.get("Server"));

        Map<String,Object> header= (HashMap<String, Object>) response.prettyPeek();
        System.out.println("  header   "+header);


    }
}
