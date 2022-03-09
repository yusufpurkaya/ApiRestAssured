package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

  public Map<String,Object> setUpTestData(){

     Map<String,Object> expectedData=new HashMap<>();
       expectedData.put("StatusCode",200);
        expectedData.put("completed",false);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("userId",1);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
return expectedData;
  }
    public static Map<String,Object> setUpTestDatastatic(){

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("StatusCode",200);
        expectedData.put("completed",false);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("userId",1);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        return expectedData;
    }
}
