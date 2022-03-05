package get_http_request;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest15 extends GMIBankBaseUrl {
    @Test
    public void test15() {
        //https://www.gmibank.com/api/tp-customers/85694
        spec3.pathParams("p1", "/tp-customers", "p2", "/85694");
        Response response = given().spec(spec3).header("Authorization", "Bearer " + generateToken()).when().get("{p1}{p2}");
        response.prettyPrint();
        response.prettyPeek();
        /*
https://www.gmibank.com/api/tp-customers/85694
        "login": "dino.kohler",
    "firstName": "Winona",
    "lastName": "Abernathy",
    "email": "winonaabernathy@gmail.com"

 */
// matcher ile
/*     response.then().assertThat().body("user.login",Matchers.equalTo("dino.kohler"),
             "firstname",Matchers.equalTo("Winona"),
             "lastname",Matchers.equalTo("Abernathy"),
             "email",Matchers.equalTo("winonaabernathy@gmail.com"));
        // json ile
        JsonPath json = response.jsonPath();
        Assert.assertEquals("dino.kohler", json.getString("login"));
        Assert.assertEquals("Winona", json.getString("firstname"));
        Assert.assertEquals("Abernathy", json.getString("lastname"));
        Assert.assertEquals("winonaabernathy@gmail.com", json.getString("email"));
        Assert.assertFalse(json.getString("country.name").equals("San"));
*/
        //MATCHERS CLASS iLE
        response.then().body("user.login", equalTo("dino.kohler")
                , "user.firstName", equalTo("Winona")
                , "user.lastName", equalTo("Abernathy")
                , "user.email", equalTo("winonaabernathy@gmail.com"));

        //JSON PATH
        JsonPath json = response.jsonPath();
        Assert.assertEquals("dino.kohler", json.get("user.login"));
        Assert.assertEquals("Winona", json.get("firstName"));
        Assert.assertEquals("Abernathy", json.get("lastName"));
        Assert.assertEquals("winonaabernathy@gmail.com", json.get("email"));

    }
}