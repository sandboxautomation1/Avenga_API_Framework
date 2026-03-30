package org.avenga.tests.books;

import io.restassured.response.Response;
import org.avenga.base.BaseTest;
import org.avenga.models.request.BookRequest;
import org.avenga.models.response.BookResponse;
import org.avenga.reporting.ExtentListener;
import org.avenga.services.Books;
import org.avenga.utils.DataUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Listeners(ExtentListener.class)
public class TS_002_GetBookById extends BaseTest {

    private final Books books = new Books();


    @Test(testName = "[TC_009_Books] Get book by ID", description = "Validate that response code is 200 and the response body is not empty", groups = { "smoke" }, priority = 0)
    public void TC_009_GetBookById() throws Exception {
        books.GET_BookById(200, "1").then().body("size()", greaterThan(0));;
    }


    @Test(testName = "[TC_010_Books] Response Time", description = "Validate that the response time is under 2 sec", groups = { "smoke", "acceptance" }, priority = 1)
    public void TC_010_PerformanceCheck() throws Exception {
        Response response = books.GET_BookById(200, "1");
        long responseTime = response.getTimeIn(TimeUnit.SECONDS);
        assertThat(responseTime, lessThanOrEqualTo(TimeUnit.SECONDS.toMillis(2)));
    }


    @Test(testName = "[TC_011_Books] Schema Validation", description = "Validate that the response code is 200 and all fields exists", groups = { "smoke", "acceptance" }, priority = 2)
    public void TC_011_SchemaValidation() throws Exception {
        BookResponse response = books.GET_BookById(200, "1").then().extract().as(BookResponse.class);

        assertThat(response.getId(), is(1));
        assertThat(response.getTitle(), notNullValue());
        assertThat(response.getDescription(), notNullValue());
        assertThat(response.getExcerpt(), notNullValue());
        assertThat(response.getPageCount(), greaterThan(0));
        assertThat(response.getPublishDate(), notNullValue());
    }


    @Test(testName = "[TC_012_Books] Check returned ID", description = "Validate that the response code is 200 and correct ID is returned", groups = {"regression"}, priority = 3)
    public void TC_012_ReturnedId() throws Exception {
        books.GET_BookById(200, "1").then().body("id", is(1));
    }


    @Test(testName = "[TC_013_Books] Invalid ID", description = "Validate that response code is 404", groups = { "smoke" }, priority = 4)
    public void TC_013_InvalidId() throws Exception {
        books.GET_BookById(404, "999999");
    }


    @Test(testName = "[TC_014_Books] Invalid ID format", description = "Validate that after sending request with wrong ID format the response status code is 400", groups = { "smoke" }, priority = 5)
    public void TC_014_InvalidIdFormat() throws Exception {
        books.GET_BookById(400, "");
    }


    @Test(testName = "[TC_015_Books] Special characters", description = "Validate that after sending request with special characters for ID the response status code is 400", groups = { "smoke" }, priority = 6)
    public void TC_015_SpecialCharacters() throws Exception {
        books.GET_BookById(400, "!@");
    }
}
