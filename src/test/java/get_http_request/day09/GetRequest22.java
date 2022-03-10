package get_http_request.day09;

import base_url.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest22 extends HerokuAppBaseUrl {

    @Test
    public void test22(){
       /*
        HerOkuAppTestData obj2=new HerOkuAppTestData();
        System.out.println(obj2.setUpTestData().get("checkin")+"-----------");
*/
spec05.pathParams("bir","booking","iki",29);

        Response response=given().spec(spec05).when().get("/{bir}/{iki}");
        response.prettyPeek();
        response.prettyPrint();
       /* HerOkuAppTestData obj3=new HerOkuAppTestData();
        HashMap<String,Object> expectedData=obj3.setUpTestData();
        System.out.println(expectedData);
*/
response.then().assertThat().body("firstname",equalTo("Ali"));

        System.out.println("------"+response.then().assertThat().body("firstname",equalTo("Ali")));
        HerOkuAppTestData obj=new HerOkuAppTestData();

        HerOkuAppTestData obj3=new HerOkuAppTestData();
        HashMap<String,Object> expectedData=obj3.setUpTestData();
        System.out.println(expectedData);


        // 1.yol de-serilialization yani json i javaya cevirip karsilastir
        // yani 2 map var expected ve actual


        HashMap<String,Object> actualData=response.as(HashMap.class);
        System.out.println(actualData+"----------");
        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
Assert.assertEquals(expectedData.get("checkin"),actualData.get("checkin"));

Assert.assertEquals((((Map)expectedData.get("bookingdates")).get("checkin")),
        (((Map)actualData.get("bookingdates")).get("checkin")));


        Assert.assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("checkout"),actualData.get("checkout"));


    }

    //1) JsonPhat

    //2) De-Serialization














      /*
   https://restful-booker.herokuapp.com/booking/47
          {
              "firstname": "Ali",
              "lastname": "Can",
              "totalprice": 500,
              "depositpaid": true,
              "bookingdates": {
                                "checkin": "2022-02-01",
                                    "checkout": "2022-02-11"
             }
          }
   1) JsonPhat
   2) De-Serialization
   */





   /*
   // -----------------Hoca Cozumu ------------*************************


        //1) URL OLUSTUR
        spec05.pathParams("first", "booking", "second", 41);

        //2) EXPECTED DATA OLUSTUR
        HerOkuAppTestData expectedObje = new HerOkuAppTestData();

        HashMap<String , Object> expectedTestDataMap = expectedObje.setUpTestData();

        System.out.println("TEST DATA iCiNDEKi EXPECTED DATA: " + expectedTestDataMap);
        // {firstname=Ali,
        // bookingdates={
        //               checkin=2022-02-01,
        //               checkout=2022-02-11},
        // totalprice=500,
        // depositpaid=true,
        // lastname=Can}

        //3) REQUEST VE RESPONSE
        Response response = given().spec(spec05).when().get("/{first}/{second}");
        response.prettyPrint();

        //4) DOGRULAMA
        //1. Yol De-Serialization

        HashMap<String, Object> actualData= response.as(HashMap.class);
        //JSON formatındaki datayı HashMap'e donüştürür.

        System.out.println("ACTUAL DATA: " + actualData);
        //{firstname=Ali,
        // bookingdates={
        //               checkin=2022-02-01,
        //               checkout=2022-02-11},
        // totalprice=500,
        // depositpaid=true,
        // lastname=Can}

        Assert.assertEquals(expectedTestDataMap.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"), actualData.get("depositpaid"));

        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkout"),
                ((Map<?, ?>) actualData.get("bookingdates")).get("checkout"));


    //2. YOL JSON PATH
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedTestDataMap.get("firstname"), json.getString("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"), json.getString("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"), json.getInt("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"), json.getBoolean("depositpaid"));

        Assert.assertEquals(((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkin"),
                json.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkout"),
                json.getString("bookingdates.checkout"));

     */

}
