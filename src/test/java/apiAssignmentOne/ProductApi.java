package apiAssignmentOne;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class ProductApi {
    @Test(priority = 0)
    public void testgetcall() {
        Response response = given().when().get("https://automationexercise.com/api/productsList");
        var statusCode = response.getStatusCode();
        System.out.println("Actual status code is " + statusCode);
        Assert.assertEquals(statusCode, 200);
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Blue Top"));
        JsonPath jsonResponse = new JsonPath(response.asString());
        var idLength = jsonResponse.getInt("products.id.size()");
        System.out.println("The length is : " + idLength);

    }

    @Test(priority = 1)
    public void testPostRequest() {
        Response response = given().when().post("https://automationexercise.com/api/productsList");
        var statusCode = response.getStatusCode();
        System.out.println("Actual status code is " + statusCode);
        Assert.assertEquals(statusCode, 200);
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("This request method is not supported."));
    }

    @Test(priority = 2)
    public void testGetAllBrandsList() {
        Response response = RestAssured.given().when().get("https://automationexercise.com/api/brandsList");
        var statusCode = response.getStatusCode();
        System.out.println("Actual status code is " + statusCode);
        Assert.assertEquals(statusCode, 200);
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("{\"id\": 33, \"brand\": \"Polo\"}"));
        JsonPath jsonPath = response.jsonPath();
        var responseLength = jsonPath.getList("brands").size();
        Assert.assertTrue(responseLength > 0);
    }

    @Test(priority = 3)
    public void testPutRequest() {
        Response response = given().when().post("https://automationexercise.com/api/brandsList");
        var statusCode = response.getStatusCode();
        System.out.println("Actual status code is " + statusCode);
        Assert.assertEquals(statusCode, 200);
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("This request method is not supported."));
    }
}