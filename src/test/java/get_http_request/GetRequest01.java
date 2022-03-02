package get_http_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
    @Test
    public void test01(){
        String url="https://restful-booker.herokuapp.com/booking";
       Response response= given().when().get(url);
      //  response.prettyPrint(); //response gelen bilgileri yazdirir

        System.out.println("status code :  "+response.statusCode()); // response in
        System.out.println("content type :  "+response.contentType());
        System.out.println("test time  :  "+response.getTime());

  //      Response response1=given().when().get(url+"2");
 //response1.prettyPrint();
response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8");
        System.out.println(" status line "+given().when().get(url).statusLine());
       // System.out.println("tum loglar   :   "+response.then().log().all().toString());
        System.out.println(response.prettyPeek()); // herseyi getiriyo bu da

/*
        System.out.println(response.getBody().peek());
        System.out.println(response.then().log().all());
   response.prettyPeek();

   bu uc satirda ayni seyi tum header i getirir

 */
    }
}
