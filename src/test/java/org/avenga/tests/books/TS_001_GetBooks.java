package org.avenga.tests.books;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.avenga.base.BaseTest;
import org.avenga.models.request.BookRequest;
import org.avenga.models.response.BookResponse;
import org.avenga.reporting.ExtentListener;
import org.avenga.services.Books;
import org.avenga.utils.DataUtils;
import org.avenga.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.avenga.utils.DataUtils.validateISO8601;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Listeners(ExtentListener.class)
public class TS_001_GetBooks extends BaseTest {

    protected Books books = new Books();


    @Test(testName = "[TC_001_Books] Get all books", description = "Validate that response code is 200 and the response body is not empty", groups = { "smoke" }, priority = 0)
    public void TC_001_GetAllBooks() throws Exception {
        books.GET_AllBooks(200).then().body("size()", greaterThan(0));;
    }


    @Test(testName = "[TC_002_Books] Response Time", description = "Validate that the response time is under 2 sec", groups = { "smoke", "acceptance" }, priority = 1)
    public void TC_002_PerformanceCheck() throws Exception {
        Response response = books.GET_AllBooks(200);
        long responseTime = response.getTimeIn(TimeUnit.SECONDS);
        assertThat(responseTime, lessThanOrEqualTo(TimeUnit.SECONDS.toMillis(2)));
    }


    @Test(testName = "[TC_003_Books] Schema Validation", description = "Validate that the response code is 200 and all fields exists", groups = { "smoke", "acceptance" }, priority = 2)
    public void TC_003_GetBookByID() throws Exception {
        books.GET_AllBooks(200).then().body("id", everyItem(greaterThan(0))).body("title", everyItem(notNullValue())).body("description", everyItem(notNullValue())).body("pageCount", everyItem(greaterThan(0))).body("excerpt", everyItem(notNullValue())).body("publishDate", everyItem(notNullValue()));
    }


    @Test(testName = "[TC_004_Books] Validate is ID unique", description = "Validate that ID is unique", groups = {"regression"}, priority = 3)
    public void TC_004_ValidID() throws Exception {
        Response response = books.GET_AllBooks(200);
        List<Integer> ids = response.jsonPath().getList("id");
        Set<Integer> uniqueIds = new HashSet<>(ids);
        Assert.assertEquals(ids.size(), uniqueIds.size(), "Found duplicate IDs!");
    }


    @Test(testName = "[TC_005_Books] Validate page count is greater than 0", description = "Validate that response code is 200 and Page Count is greater than 0", groups = { "smoke" }, priority = 4)
    public void TC_005_PageCount() throws Exception {
        books.GET_AllBooks(200).then().body("pageCount", everyItem(greaterThan(0)));
    }


    @Test(testName = "[TC_006_Books] Publish date format", description = "Validate that response code is 200 and Publish Date format is correct", groups = { "smoke" }, priority = 5)
    public void TC_006_PublishDateFormat() throws Exception {
        Response response = books.GET_AllBooks(200);
        List<String> dates = response.jsonPath().getList("publishDate");
        dates.forEach(DataUtils::validateISO8601);
    }


    @Test(testName = "[TC_007_Books] Title field", description = "Validate that response code is 200 and Title is not null", groups = { "smoke" }, priority = 6)
    public void TC_007_Title() throws Exception {
        books.GET_AllBooks(200).then().body("title", notNullValue());
    }


    @Test(testName = "[TC_008_Books] Description field", description = "Validate that response code is 200 and Description is not null", groups = { "smoke" }, priority = 7)
    public void TC_008_Description() throws Exception {
        books.GET_AllBooks(200).then().body("description", notNullValue());
    }
}
