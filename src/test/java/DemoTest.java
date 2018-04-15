import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class DemoTest {
    @Test
    public void testJson(){
        when().get("http://127.0.0.1:8000/restassured.json")
        .then()
            .body("store.book.category", hasItems("fiction"))
            .body("store.book[0].category", equalTo("reference"))
            .body("store.book[-1].category", equalTo("fiction"))
            .body("store.book.findAll { book -> book.price >= 5 && book.price <= 15 }.size", equalTo(3))
            .body("store.book.find { book -> book.price == 12.99f }.price",  equalTo(12.99f));
    }
}
