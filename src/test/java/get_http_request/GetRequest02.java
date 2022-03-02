package get_http_request;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest02 {

    @Test
    public void test02(){
        String url="https://reqres.in/api/users";
       Response response= given().when().get(url);
        response.prettyPrint(); // response u yazdirir
        response.prettyPeek(); // postman da ki header i getirir

// header i test
        response.then().assertThat().statusCode(200).// response un status code i 200 mu
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");


// json path ile sorgulama veya Matcher ile
        // Body i test
        response.then().body("data[0].first_name", equalTo("George"),
                "data[0].last_name", equalTo("Bluth"),
                "data[0].email", equalTo("george.bluth@reqres.in"));
// ustteki kodu matchers i kaldirip da yapabiliriz statik yap deriz matcher.equalto yu yap deriz
        response.then().body("data[0].first_name", equalTo("George"),
                "data[0].last_name",equalTo("Bluth"),
                "data[0].email",equalTo("george.bluth@reqres.in"));

        response.then().body("data[1].id",equalTo(2),"data[1].email",equalTo("janet.weaver@reqres.in"),
                "data[1].first_name",equalTo("Janet"),"data[1].last_name",equalTo("Weaver"),
                "data[1].avatar",equalTo("https://reqres.in/img/faces/2-image.jpg"));


    }

}
