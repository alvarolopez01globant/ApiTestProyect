package org.globant.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.globant.configuration.ConfigurationManager;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setupSuite() {
        RestAssured.baseURI = ConfigurationManager.getBaseUrl();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

}
