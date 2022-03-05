package get_http_request.day07;

import base_url.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest17 extends GMIBankBaseUrl {
    /*
   http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın

{
   "firstName": "Della",
   "lastName": "Heaney",
   "email": "ricardo.larkin@yahoo.com",
   "mobilePhoneNumber": "123-456-7893",
}
    */

    @Test
    public void test17(){
     // 1 url olustur
        spec3.pathParams("bir","tp-customers","iki",114351);
        Response response=given().spec(spec3).header("Authorization", "Bearer " + generateToken()).when().get("/{bir}/{iki}");
       response.prettyPrint();
        // 2 expected data
        Map<String,Object> expecteddata=new HashMap<>();
        expecteddata.put("firstName","Della");
      expecteddata.put("lastName","Heaney");
      expecteddata.put("email","ricardo.larkin@yahoo.com");
      expecteddata.put("mobilePhoneNumber","123-456-7893");



        // 3 request and response
        Map<String,Object> actuelData=response.as(HashMap.class);
        Map<String,Object> actuelData2=response.as(Map.class); // bu Map oldu hashmap degilde

        // dogrulama yap
        Assert.assertEquals(expecteddata.get("firstName"),actuelData.get("firstName"));
        Assert.assertEquals(expecteddata.get("lastName"),actuelData.get("lastName"));
        Assert.assertEquals(expecteddata.get("email"),actuelData.get("email"));
        Assert.assertEquals(expecteddata.get("mobilePhoneNumber"),actuelData.get("mobilePhoneNumber"));

    }


}
