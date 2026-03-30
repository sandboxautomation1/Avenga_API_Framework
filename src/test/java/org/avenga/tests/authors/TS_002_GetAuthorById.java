package org.avenga.tests.authors;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.avenga.base.BaseTest;
import org.avenga.models.request.AuthorRequest;
import org.avenga.models.request.BookRequest;
import org.avenga.models.response.AuthorResponse;
import org.avenga.models.response.BookResponse;
import org.avenga.reporting.ExtentListener;
import org.avenga.services.Authors;
import org.avenga.services.Books;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Listeners(ExtentListener.class)
public class TS_002_GetAuthorById extends BaseTest {

    private final Authors authors = new Authors();


    @Test(testName = "[TC_007_Authors] Get author by ID", description = "Validate that response code is 200 and the response body is not empty", groups = { "smoke" }, priority = 0)
    public void TC_007_GetAuthorById() throws Exception {
        authors.GET_AuthorById(200, "1").then().body("size()", greaterThan(0));;
    }


    @Test(testName = "[TC_008_Authors] Response Time", description = "Validate that the response time is under 2 sec", groups = { "smoke", "acceptance" }, priority = 1)
    public void TC_008_ResponseTime() throws Exception {
        Response response = authors.GET_AuthorById(200, "1");
        long responseTime = response.getTimeIn(TimeUnit.SECONDS);
        assertThat(responseTime, lessThanOrEqualTo(TimeUnit.SECONDS.toMillis(2)));
    }


    @Test(testName = "[TC_009_Authors] Schema Validation", description = "Validate that the response code is 200 and all fields exists", groups = { "smoke", "acceptance" }, priority = 2)
    public void TC_009_SchemaValidation() throws Exception {
        authors.GET_AuthorById(200, "1").then().body("id", everyItem(greaterThan(0))).body("idBook", everyItem(notNullValue())).body("firstName", everyItem(notNullValue())).body("lastName", everyItem(notNullValue()));
    }


    @Test(testName = "[TC_010_Authors] Invalid ID", description = "Validate that response code is 404", groups = { "smoke" }, priority = 3)
    public void TC_010_InvalidId() throws Exception {
        authors.GET_AuthorById(404, "999999");
    }


    @Test(testName = "[TC_011_Authors] Special characters", description = "Validate that after sending request with special characters for ID the response status code is 400", groups = { "smoke" }, priority = 4)
    public void TC_011_SpecialCharacters() throws Exception {
        authors.GET_AuthorById(400, "!@");
    }


    @Test(testName = "[TC_012_Authors] Negative ID", description = "Validate that after sending request with negative ID the response status code is 400", groups = { "smoke" }, priority = 5)
    public void TC_012_NegativeId() throws Exception {
        authors.GET_AuthorById(400, "-1");
    }
}
