package apiAssignmentOne;

import dataProvider.*;
import io.restassured.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import java.util.logging.*;

public class ProductApi {
    private String url = "";

    private Logger logger = Logger.getLogger(ProductApi.class.getName());

    @Test(priority = 0)
    public void testGetcall() {
        url = ConfigReader.getURL();
        Response response = get(url);
        var statuscode = response.getStatusCode();
        //  Assert.assertEquals(statusCode, 200);
        logger.log(Level.INFO, "get status code " + statuscode);
    }

    @Test(priority = 1)
    public void validateList() {
        url = ConfigReader.getURL();
        var getALLProductList = given().when().get(url).then().log().all().toString();
        logger.log(Level.INFO, "List return by API is " + getALLProductList);
    }

    @Test(priority = 2)
    public void validateContent() {
        url = ConfigReader.getURL();
        Response response = RestAssured.given().when().get(url);
        var statusCode = response.getStatusCode();
        JsonPath jsonPath = new JsonPath(response.asString());
        logger.info("my json response: " + jsonPath.get("products"));
        var productsArray = jsonPath.get("products");
        var idLength = jsonPath.getInt("products.id.size()");
        for (int counter = 0; counter < productsArray.toString().length(); counter++) {
            String productName = jsonPath.getString("products[" + counter + "].name");
            logger.log(Level.INFO, "Product name is  " + productName);
            if (productName == "Cotton V-Neck T-Shirt") {
                Assert.assertEquals(productName, "Cotton V-Neck T-Shirt");
                Assert.assertEquals(jsonPath.getString("products[" + counter + "].price"), "Rs. 400");
                Assert.assertEquals(jsonPath.getString("products[" + counter + "].brand"), "H&M");
                Assert.assertEquals(jsonPath.getString("products[" + counter + "].category.category"), "Top");
                logger.log(Level.INFO, "Product name is  " + productName);
            }
        }
    }

    @Test(priority = 3)
    public void testPutRequest() {
        url = ConfigReader.getURL();
        var response = given().when().get(url).then().extract().asString();
        JsonPath jsonResponse = new JsonPath(response);
        var idLength = jsonResponse.getInt("products.id.size()");
        Assert.assertEquals(34, idLength);
        logger.log(Level.INFO, "Length is " + idLength);
    }
}