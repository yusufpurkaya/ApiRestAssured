package get_http_request.day10;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest23 extends DummyBaseUrl {

    @Test
    public void test23(){
        spec02.pathParams("bir","api","iki","v1","uc","employees");
        Response response=given().spec(spec02).contentType(ContentType.JSON).when().get("/{bir}/{iki}/{uc}");
/*     //  bazen contanttype da hata verir    contentType(ContentType.JSON)  bunu ekle
   http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
   Status kodun 200 olduğunu,
   14. Çalışan isminin "Haley Kennedy" olduğunu,
   Çalışan sayısının 24 olduğunu,
   Sondan 3. çalışanın maaşının 675000 olduğunu
   40,21 ve 19 yaslarında çalışanlar olup olmadığını
   10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi

   {
           "id": 10,
           "employee_name": "Sonya Frost",
           "employee_salary": 103600,
           "employee_age": 23,
           "profile_image": ""
    }

     olduğunu test edin.
   */
response.prettyPrint();
response.prettyPeek();
        DummyTestData expecteddata=new DummyTestData();
        HashMap<String,Object> expectedData= expecteddata.setUpTestData();
        HashMap<String,Object> actualData=response.as(HashMap.class);

        // Dogrulama
        // 1- deSerialization
        Assert.assertEquals(expectedData.get("statusCode"),response.statusCode());
       //14. Çalışan isminin "Haley Kennedy" olduğunu,
        Assert.assertEquals(expectedData.get("ondorduncucalisan"),((Map)((List)(actualData.get("data"))).get(13)).get("employee_name"));
       // Çalışan sayısının 24 olduğunu,
       Assert.assertEquals(expectedData.get("calisansayisi"),((List)actualData.get("data")).size());
       //Sondan 3. çalışanın maaşının 675000 olduğunu
       int datasize=((List<?>) actualData.get("data")).size();

        Assert.assertEquals(expectedData.get("sondanucuncucalisaninmaasi"),
                ((Map) ((List<?>) actualData.get("data")).get(datasize-3)).get("employee_salary"));

//40,21 ve 19 yaslarında çalışanlar olup olmadığını
       List<Integer> actuelyaslar=new ArrayList<>();
        for (int i = 0; i < datasize;i++) {
            actuelyaslar.add((Integer) ((Map) ((List)actualData.get("data")).get(i)).get("employee_age"));;
        }
Assert.assertTrue(actuelyaslar.containsAll((Collection<?>) expectedData.get("arananyaslar")));
        //Assert.assertTrue(actualYasListesi.containsAll((Collection<?>) expectedTestDataMap.get("arananyaslar")));

        //Assert.assertEquals(((HashMap)(expectedData.get("onuncucalisan"))).get("employee_name"),actualData.get("data[9].employee_name"));



        // 1 json path ile
        JsonPath json=response.jsonPath();
        System.out.println(json.peek()+"-----");
        //Assert.assertEquals(json.);

/*
   //1) URL OLUSTUR
        spec02.pathParams("bir", "api", "iki", "v1", "uc", "employees");

        //2) EXPECTED DATA OLUSTUR
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedTestDataMap = expectedObje.setUpTestData();
        System.out.println("EXPECTED TEST DATA: " + expectedTestDataMap);
        //EXPECTED TEST DATA: {onuncucalisan=
        //                                  {profile_image=,
        //                                  employee_name=Sonya Frost,
        //                                  employee_salary=103600,
        //                                  id=10, employee_age=23},
        //                     ondurduncucalisan=Haley Kennedy,
        //                     arananyaslar=[40, 21, 19],
        //                     calisansayisi=24,
        //                     statusCode=200,
        //                     sondanucuncucalisaninmaasi=675000}

        //3) REQUEST VE RESPONSE OLUSTUR
        Response response = given().spec(spec02).contentType(ContentType.JSON).when().get("/{bir}/{iki}/{uc}");
        //response.prettyPrint();

        //4) DOGRULAMA
        //De-Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("ActualDataMap = " + actualDataMap);

        //Status kodun 200 olduğunu,
        Assert.assertEquals(expectedTestDataMap.get("statusCode"), response.getStatusCode());

        //14. Çalışan isminin "Haley Kennedy" olduğunu,
        Assert.assertEquals(expectedTestDataMap.get("ondorduncucalisan"),
                ((Map)((List)actualDataMap.get("data")).get(13)).get("employee_name"));

        //Çalışan sayısının 24 olduğunu,
        Assert.assertEquals(expectedTestDataMap.get("calisansayisi"),
                ((List<?>) actualDataMap.get("data")).size());

        //Sondan 3. çalışanın maaşının 675000 olduğunu
        //1. Yol
        Assert.assertEquals(expectedTestDataMap.get("sondanucuncucalisaninmaasi"),
                ((Map)((List)actualDataMap.get("data")).get(((List)actualDataMap.get("data")).size()-3)).get("employee_salary"));

        //2. Yol

        int dataSize = ((List<?>) actualDataMap.get("data")).size();

        Assert.assertEquals(expectedTestDataMap.get("sondanucuncucalisaninmaasi"),
                ((Map)((List<?>) actualDataMap.get("data")).get(dataSize-3)).get("employee_salary"));

        //40,21 ve 19 yaslarında çalışanlar olup olmadığını

        List<Integer> actualYasListesi = new ArrayList<>();

        for(int i =0; i<dataSize; i++){
            actualYasListesi.add(((Integer) ((Map)((List<?>) actualDataMap.get("data")).get(i)).get("employee_age")));
        }

        Assert.assertTrue(actualYasListesi.containsAll((Collection<?>) expectedTestDataMap.get("arananyaslar")));

//2. Yol
        List<Integer> employee_age = new ArrayList<>();
        for(int i=0 ; i < ((List)actualDataMap.get("data")).size() ; i++){
            employee_age.add((Integer) ((Map)((List)actualDataMap.get("data")).get(i)).get("employee_age"));
        }

 */
/*
List<Integer> employee_age = new ArrayList<>();
for(int i=0 ; i < ((List)actualData.get("data")).size() ; i++){
    employee_age.add((Integer) ((Map)((List)actualData.get("data")).get(i)).get("employee_age"));
}         Assert.assertTrue(employee_age.containsAll((Collection<?>) expectedTestDataMap.get("arananYaslar")));
 */



    }
}
