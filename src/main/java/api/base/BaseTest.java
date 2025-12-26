package api.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

/**
 * Base test class for API automation framework
 * Provides common setup for all test classes
 * 
 */
public class BaseTest {

    protected static final String BASE_URI = "https://fakestoreapi.com";
    protected static final String BASE_PATH = "";
    
    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
        
        // Request Specification
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
        
        // Response Specification
        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    /**
     * Get request specification with base configuration
     * @return RequestSpecification
     */
    protected RequestSpecification getRequestSpec() {
        return RestAssured.given().spec(requestSpec);
    }

    /**
     * Get response specification with base configuration
     * @return ResponseSpecification
     */
    protected ResponseSpecification getResponseSpec() {
        return responseSpec;
    }
}

