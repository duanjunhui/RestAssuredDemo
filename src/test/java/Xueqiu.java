import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Xueqiu {
    @Test
    public void testSearch() {
        //信任https的任何证书
        useRelaxedHTTPSValidation();

        //given开头表示输入数据
        given().log().all()
                //query请求
                .queryParam("code", "geli")
                //头信息
                .header("Cookie", "xq_a_token=229a3a53d49b5d0078125899e528279b0e54b5fe; xq_a_token.sig=oI-FfEMvVYbAuj7Ho7Z9mPjGjjI; xq_r_token=8a43eb9046efe1c0a8437476082dc9aac6db2626; xq_r_token.sig=Efl_JMfn071_BmxcpNvmjMmUP40; __utma=1.1570133504.1523678448.1523678448.1523678448.1; __utmc=1; __utmz=1.1523678448.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; Hm_lvt_1db88642e346389874251b5a1eded6e3=1523678448; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1523678448; u=861523678453037; device_id=c12024e4bf0018fc5d8ff5d352f40063")
                //表示触发条件
                .when()
                .get("http://xueqiu.com/stock/search.json")
                //对结果断言
                .then()
                .log().all()
                //状态码断言
                .statusCode(200)
                //字段断言
                .body("stocks.name", hasItems("格力电器"));
        //.body("stocks.code", hasItems("geli"));
    }
    @Test
    public void testLogin() {
        useRelaxedHTTPSValidation();

        given()
                .header("User-Agent", "Xueqiu Android 10.5")
                .queryParam("_t", "1GENYMOTION6863ec148419d5df6eac0af60508137f.5204427043.1523766643409.1523772559508")
                .queryParam("_s", "97872d")
                .cookie("xq_a_token", "565b4ce5b4033c835809ae06cea936844fe1bb5e")
                .cookie("u", "5204427043")
                .formParam("client_secret", "txsDfr9FphRSPov5oQou74")
                .formParam("telephone", "18518571138")
                .formParam("password", "558b1aa94403304b0f0ac1eae2884d9f")
                .when().post("https://api.xueqiu.com/provider/oauth/token")
                .then()
                .statusCode(200)
                .body("uid", equalTo(1038157532));


    }


}
