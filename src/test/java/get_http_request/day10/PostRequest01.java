package get_http_request.day10;

import base_url.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends HerokuAppBaseUrl {
    @Test
    public void test01(){

       // url olustur
        spec05.pathParam("bir","booking");

        // expected data
        HerOkuAppTestData testData=new HerOkuAppTestData();
       JSONObject expectedRequestData= testData.steUptestandRequestData();

        // Request Response
        Response response=given().
                accept(ContentType.JSON).
                auth().basic("Admin","password123").
                spec(spec05).
                body(expectedRequestData.toString()).when().post("/{bir}");
        //       eger map olsaydi       toString e gerek kalmazdi  ama HashMap
// ContentType.JSON     contentType("application/json; charset=utf-8")
        // application/json
       response.prettyPeek();
       response.prettyPrint();
// dogrulama
        // jsonPath ile

        JsonPath json=response.jsonPath();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(expectedRequestData.getString("firstname"),json.getString("firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"),json.getString("lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"),json.getInt("totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),json.getBoolean("depositpaid"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),json.getString("bookingdates.checkin"));
        //  getJSONObject  bunu kullanmasak data casting vs ugrasirdik
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),json.getString("bookingdates.checkout"));

      // DeSerialization
       Map<String,Object> actualData2=response.as(HashMap.class);
        HashMap <String,Object> actualData=response.as(HashMap.class);
        Assert.assertEquals(actualData.get("firstname"),expectedRequestData.getString("firstname"));
      Assert.assertEquals(actualData.get("lastname"),expectedRequestData.getString("lastname"));
Assert.assertEquals(actualData.get("totalprice"),expectedRequestData.get("totalprice"));
Assert.assertEquals(actualData.get("depositpaid"),expectedRequestData.get("depositpaid"));
Assert.assertEquals(((Map)actualData2.get("bookingdates")).get("checkin"),expectedRequestData.getJSONObject("bookingdates").get("checkin"));

/*
   https://restful-booker.herokuapp.com/booking
   { "firstname": "Ali",
              "lastname": "Can",
              "totalprice": 500,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2022-03-01",
                  "checkout": "2022-03-11"
               }
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
}
   "booking": {
       "firstname": "Ali",
       "lastname": "Can",
       "totalprice": 500,
       "depositpaid": true,
       "bookingdates": {
                           "checkin": "2022-03-01",
                            "checkout": "2022-03-11"
       }*/
/*
//1) URL OLUSTUR
        spec05.pathParam("parametre1", "booking");

        //2) EXPECTED DATA
        HerOkuAppTestData testData = new HerOkuAppTestData();
        JSONObject expectedRequestData = testData.setUpTestAndRequestData();
        System.out.println(expectedRequestData);

        //3) REQUEST VE RESPONSE

        Response response = given()
                            .contentType(ContentType.JSON)
                            .auth()
                            .basic("admin","password123")
                            .spec(spec05)
                            .body(expectedRequestData.toString())
                            .when()
                            .post("/{parametre1}");

        response.prettyPrint();
 */


        /*   Emrah Bey

           https://restful-booker.herokuapp.com/booking
           {
                       "firstname": "Ali",
                      "lastname": "Can",
                      "totalprice": 500,
                      "depositpaid": true,
                      "bookingdates": {
                          "checkin": "2022-03-01",
                          "checkout": "2022-03-11"
                       }
        }
        gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
        }
           "booking": {
               "firstname": "Ali",
               "lastname": "Can",
               "totalprice": 500,
               "depositpaid": true,
               "bookingdates": {
                                   "checkin": "2022-03-01",
                                    "checkout": "2022-03-11"
               }
           }
        }
        olduğunu test edin


            // 1) URL OLUSTUR
            spec05.pathParam("parametre1", "booking");
            // 2) EXPECTED DATA
            HerOkuAppTestData testData = new HerOkuAppTestData();

            JSONObject expectedRequestData = testData.setupTestAndRequestData();

            System.out.println("expectedRequestData = " + expectedRequestData);
            // 3) REQUEST AND RESPONSE
            Response response = given().
                    contentType(ContentType.JSON).
                    auth().
                    basic("admin", "password123").
                    spec(spec05).
                    body(expectedRequestData.toString()).
                    when().
                    post("/{parametre1}");
            response.prettyPrint();
            // JSONObject'te toString() kullanmalıyız => body(expectedRequestData.toString())
            // 4) DOGRULAMA
            // 1. WAY JSON PATH
            JsonPath json = response.jsonPath();
            response.then().assertThat().statusCode(200);
            Assert.assertEquals(expectedRequestData.getString("firstname") , json.getString("booking.firstname"));
            Assert.assertEquals(expectedRequestData.getString("lastname") , json.getString("booking.lastname"));
            Assert.assertEquals(expectedRequestData.getInt("totalprice") , json.getInt("booking.totalprice"));
            Assert.assertEquals(expectedRequestData.getBoolean("depositpaid") , json.getBoolean("booking.depositpaid"));
            // 1.Yol Casting
            Assert.assertEquals(((JSONObject)expectedRequestData.get("bookingdates")).getString("checkin") ,
                    json.getString("booking.bookingdates.checkin"));
            Assert.assertEquals(((JSONObject)expectedRequestData.get("bookingdates")).getString("checkout") ,
                    json.getString("booking.bookingdates.checkout"));
            // 2. Yol
            Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin") ,
                    json.getString("booking.bookingdates.checkin"));
            Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout") ,
                    json.getString("booking.bookingdates.checkout"));

         */
    }
}
