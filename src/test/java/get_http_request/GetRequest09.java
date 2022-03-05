package get_http_request;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.RectangularShape;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest09  {
    //extends DummyBaseUrl
   /*
http://dummy.restapiexample.com/api/v1/employee/12 URL'E GiT.
1) Matcher CLASS ile
2) JsonPath ile dogrulayin.
*/

@Test
    public void test09(){
    //            http://dummy.restapiexample.com/api/v1/employee/12
  //  spec02.pathParams("first","/api","second","/v1","thirt","/employee","fourth","/12");
   // Response response=given().spec(spec02).when().get("{first}{second}{thirt}{fourth}");


    String url="http://dummy.restapiexample.com/api/v1/employee/12";
    Response response=given().when().get(url);
    response.prettyPrint();

    //1) Matcher CLASS ile
/*
    response.then().assertThat().body("data.id", equalTo(12),
            "data.employee_name",equalTo("Quinn Flynn"),
            "data.employee_salary",equalTo(342000),"data.employee_age",equalTo(22),"data.profile_image","");
*/

    //2) JsonPath ile dogrulayin.
    JsonPath json=response.jsonPath();
    Assert.assertEquals(12,json.getInt("data.id"));
    Assert.assertEquals("Quinn Flynn",json.getString("data.employee_name"));
Assert.assertEquals( 342000,json.getInt("data.employee_salary"));
Assert.assertEquals(22,json.getInt("data.employee_age"));
Assert.assertEquals("",json.getString("data.profile_image"));

/*
 @Test
    public void test09(){
        spec02.pathParams("birinci", "api",
                    "ikinci", "v1",
                            "ucuncu", "employee",
                            "dorduncu", "12");

        //http://dummy.restapiexample.com
        Response response = given().spec(spec02).when().get("/{birinci}/{ikinci}/{ucuncu}/{dorduncu}");
        // "/{birinci}/{ikinci}/{ucuncu}/{dorduncu}" => /api/v1/employee/12
        response.prettyPrint();

        //MATCHERS CLASS iLE
        response.then().statusCode(200).contentType(ContentType.JSON)
                .body("data.employee_name", equalTo("Quinn Flynn"),
                        "data.employee_salary", equalTo(342000),
                        "data.employee_age", equalTo(22));

        //JSON PATH
        JsonPath json = response.jsonPath();
        System.out.println(json.getString("data.employee_name"));
        System.out.println(json.getInt("data.employee_age"));
        System.out.println(json.getInt("data.employee_salary"));

        assertEquals("Quinn Flynn",json.getString("data.employee_name"));
        assertEquals(342000,json.getInt("data.employee_salary"));
        assertEquals(22,json.getInt("data.employee_age"));
    }
}

 */






}


}
