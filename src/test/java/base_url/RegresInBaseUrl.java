package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RegresInBaseUrl {
   protected RequestSpecification spec01;

   @Before
    public void setUp() {
        spec01 = new RequestSpecBuilder().setBaseUri("https://regres.in").build();
    }
}
