package get_http_request;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends GMIBankBaseUrl {
    /*
http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın

"firstName": "Melva",
"lastName": "Bernhard",
"email": "chas.kuhlman@yahoo.com"
"zipCode": "40207"

"country" "name": "San
"login": "delilah.metz"
*/
    @Test
    public void test14(){
        spec3.pathParams("p1","/tp-customers","p2", "/110472");
        Response response=given().spec(spec3).header("Authorization","Bearer "+generateToken()).when().get("{p1}{p2}");
        response.prettyPrint();
        response.prettyPeek();
// matcher ile
        response.then().assertThat().body("firstName", Matchers.equalTo("Melva"),
                "lastName",Matchers.equalTo("Bernhard"),

                "email",Matchers.equalTo("chas.kuhlman@yahoo.com"),

                "zipCode",Matchers.equalTo("40207"),
                "country.name",Matchers.not("San"),
                "user.login",Matchers.equalTo("delilah.metz"));
// json ile
        JsonPath json = response.jsonPath();
        Assert.assertEquals("Melva", json.getString("firstName"));
        Assert.assertEquals("Bernhard", json.getString("lastName"));
        Assert.assertEquals("chas.kuhlman@yahoo.com", json.getString("email"));
        Assert.assertEquals("40207", json.getString("zipCode"));
        Assert.assertFalse(json.getString("country.name").equals("San"));
        Assert.assertEquals("delilah.metz", json.getString("user.login"));


    }



}
