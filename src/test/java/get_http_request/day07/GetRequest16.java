package get_http_request.day07;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest16 extends JsonPlaceHolderBaseUrl {
    @Test
    public void test16(){
        /*
   https://jsonplaceholder.typicode.com/todos/7

   {  // bu bilgiler api de json formatinda ama java da okumak istiyorum
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false
}
    */
      // 1 url olustur
        spec4.pathParams("p1","todos","p2",7);

      //  2  expected data olustur
        // cunku json da    map de key value yapisinda

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",7);
        expectedData.put("title","illo expedita consequatur quia in");
        expectedData.put("completed",false);


        System.out.println(expectedData);

        // 3  Request Response
        Response response=given().spec(spec4).when().get("/{p1}/{p2}");


response.prettyPrint();
response.prettyPeek();

        //datayi javadan>>Json'a donusturme isi= serialization
//datayi Jsondan>>Java'ya donusturme isi= De-serialization
//expected ve actual datalarin ayni formatta karsilastirilmasi icin yapilan donusturme islemleri
         // bunun icin   zaten java  run edince uyarir jacksondatabind veya baska
         // dependencies yukle der
//Serialization is the process of converting an object into a stream of bytes to store the object or transmit it to memory,
// a database, or a file.

        Map<String,Object> actualData=response.as(HashMap.class);
        // De-serialization yaptik yani json i javaya donusturduk


        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));

    }
}
