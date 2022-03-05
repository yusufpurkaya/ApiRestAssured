package utilities;

import base_url.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {
   /* generateToken(); siteye gidiyo ve kullanici adi ve passwordle bir token aliyor her onune gelen
      api uzerinden bilgilere ulasmasin. BearerToken aslinda postmende manuel yapmistik bu islemi
    public static void main(String[] args) {
        String guncelToken = generateToken();
        System.out.println(guncelToken);
    }
*/

    public static String generateToken(){
        String username = "Batch44Api";
        String password = "Batch44+";

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        String endPoint = "https://www.gmibank.com/api/authenticate";

        Response response = given().contentType(ContentType.JSON).body(map).when().post(endPoint);
//   contentType(ContentType.JSON) bunu yazma sebebimiz bazen hata verebiliyo diyoruz ki sana json formatin
 // bir veri gonderiyorum
        JsonPath token = response.jsonPath();
// id_token  "https://www.gmibank.com/api/authenticate" adresindeki response dan geldi
        return token.getString("id_token");
    }

}
