package get_http_request;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends GMIBankBaseUrl {
@Test
    public void test13(){

    //http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın
    //    “firstName”: “Della”,
    //    “lastName”: “Heaney”,
    //    “mobilePhoneNumber”: “123-456-7893”,
    //    “address”: “75164 McClure Stream”,
    //    “country” : “USA”
    //    “state”: “New York43"
    //    “CREDIT_CARD”,hesabında 69700$ ,
    //    “CHECKING” hesabında 11190$

    //   spec01.pathParams("parametre1","api","parametre2","users");

    //                              https://reqres.in      /   api      /  users
    //   Response response=given().spec(spec01).when().get("{/{parametre1}/{parametre2}");

    spec3.pathParams("p1","/tp-customers","p2", "/114351");
    Response response=given().spec(spec3).header("Authorization","Bearer "+generateToken()).when().get("{p1}{p2}");
    response.prettyPrint();
    response.prettyPeek();

// Matcherclass ile
    response.then().assertThat().body("firstName", Matchers.equalTo("Della"),
            "lastName",Matchers.equalTo("Heaney"),
            "mobilePhoneNumber",Matchers.equalTo("123-456-7893"),
            "phoneNumber",Matchers.equalTo("213-456-7893"),
            "address",Matchers.equalTo("75164 McClure Stream"),
            "country.name",Matchers.equalTo("USA"),
            "state",Matchers.equalTo("New York43"),"accounts.balance[0]",Matchers.equalTo(69700),
            "accounts.balance[1]",Matchers.equalTo(11190));

    JsonPath json=response.jsonPath();
    Assert.assertEquals("Della",json.getString("firstName"));
    Assert.assertEquals("Heaney",json.getString("lastName"));
    Assert.assertEquals("123-456-7893",json.getString("mobilePhoneNumber"));
    Assert.assertEquals("213-456-7893",json.getString("phoneNumber"));
    Assert.assertEquals("75164 McClure Stream",json.getString("address"));
    Assert.assertEquals("USA",json.getString("country.name"));
    Assert.assertEquals("New York43",json.getString("state"));
    Assert.assertEquals(69700,json.getInt("accounts[0].balance[0]"));
    Assert.assertEquals(11190, json.getInt("accounts[0].balance"));
/*
 Assert.assertEquals(69700, json.getInt("accounts[0].balance"));
        Assert.assertEquals(11190, json.getInt("accounts[1].balance"));
 */


}

}
