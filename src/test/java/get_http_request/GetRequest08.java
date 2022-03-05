package get_http_request;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest08  {

//extends DummyBaseUrl
    @Test
    public void test08(){
//spec02.pathParams("p1","/api","p2","/v1","p3","/employees");
        //     http://dummy.restapiexample.com         /api           /v1    /employees
//                   Response response=given().spec(spec02).when().get("{p1}{p2}{p3}");



        /*
   http://dummy.restapiexample.com/api/v1/employees             url'inde bulunan

  1) Butun calisanlarin isimlerini consola yazdıralim
  2) 3. calisan kisinin ismini konsola yazdıralim
  3) Ilk 5 calisanin adini konsola yazdiralim
  4) En son calisanin adini konsola yazdiralim
*/
        String url="http://dummy.restapiexample.com/api/v1/employees";
        Response response=given().when().get(url);
        response.prettyPrint();

        // jsonpath ile test
      // 1
        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getList("data.employee_name"));

        // 2
        System.out.println(jsonPath.getString("data[2].employee_name"));
        // 3
        //1. Yol
        for (int i = 0; i < 5; i++) {
            System.out.println(i+1 + ". calisan: " +jsonPath.getString("data[" + i + "].employee_name"));
        }

        //2. Yol
        System.out.println(jsonPath.getString("data.employee_name[0,1,2,3,4]"));

        //3. Yol
        System.out.println(jsonPath.getList("data[0,1,2,3,4].employee_name"));





        // Ilk 5 calisanin adini konsola yazdiralim lambda ile
        jsonPath.getList("data.employee_name").stream().limit(4).forEach(t-> System.out.println(t));

      /*  List<String> liste= Collections.singletonList(jsonPath.getString("data.employee_name"));
        for (String each:liste) {
           List<String> liste2;
           liste2.add(each);
        }
*/




        //4) En son calisanin adini konsola yazdiralim
        System.out.println(jsonPath.getString("data[-1].employee_name"));
        System.out.println(jsonPath.getString("data.employee_name[-1]"));
    }


}
