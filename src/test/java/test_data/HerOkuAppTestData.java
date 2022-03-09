package test_data;

import java.util.HashMap;

public class HerOkuAppTestData {
    public HashMap<String,Object> setUpTestData(){
        HashMap<String,Object> bookingDates=new HashMap<>();
        bookingDates.put("checkin","2022-02-01");
        bookingDates.put("checkout","2022-02-11");

        HashMap<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstname","Ali");
        expectedData.put("lastname","Can");
        expectedData.put("totalprice",500);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingDates);


        /*expectedData.put("bookingdates.checkin","2022-02-01");
          expectedData.put("bookingdates.checkout","2022-02-11");
          */
return expectedData;
    }
}
