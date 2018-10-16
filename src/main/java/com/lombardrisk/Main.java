package com.lombardrisk;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.collection.IsIn;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.StringContains;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] arg) throws IOException {
//        XmlPath path = when().get("http://10.88.4.131:4444/grid/console").getBody().htmlPath();
//        List<Node> list = path.getList("html.body.div.div.div.div.div.p.img", Node.class);
//        int count = list.stream()
//                .filter(node -> node.getAttribute("src").contains("firefox.png"))
//                .filter(node -> !"busy".equals(node.getAttribute("class")))
//                .collect(Collectors.toList()).size();
//        System.out.println("available firefox nodes: " + count);
        //17

        Random random = new Random();
        StringBuffer jwinS = new StringBuffer();
        for (int i = 0; i < 17; i++) {
            jwinS.append(random.nextInt(9));
        }
        String jwinname = "colline" + jwinS.toString() + "a";
        String baseUri = "http://10.87.7.65:8080/";

//        String sessionId = RestAssured.given().log().ifValidationFails().baseUri(baseUri)
//                .get("colline/index.do?j_winname=" + jwinname).getSessionId();
        Response response = RestAssured.given().log().ifValidationFails().baseUri(baseUri)
                .formParam("j_username", "gukai")
                .formParam("j_password", "1")
                .formParam("submit", "Enter")
                .formParam("j_winname", jwinname)
                .post("colline/j_security_check");
        String sessionId = response.getSessionId();
        response.then().statusCode(IsIn.isIn(Arrays.asList(200, 302)));

        response = RestAssured.given().log().ifValidationFails().baseUri(baseUri).sessionId(sessionId)
                .get("colline/index.do?j_winname=" + jwinname);
        if (response.asString().contains("name=\"continue\"")) {
            RestAssured.given().log().ifValidationFails().baseUri(baseUri).sessionId(sessionId)
                    .formParam("j_winname", jwinname)
                    .formParam("continue", "Continue")
                    .post("colline/index.do");
            response = RestAssured.given().log().ifValidationFails().baseUri(baseUri).sessionId(sessionId).redirects().allowCircular(true)
                    .get("colline/index.do?j_winname=" + jwinname + "&continue=Continue");
        }
        response.then().statusCode(200).body(IsNot.not(StringContains.containsString("name=\"loginForm\"")));
        response.prettyPrint();
    }
}
