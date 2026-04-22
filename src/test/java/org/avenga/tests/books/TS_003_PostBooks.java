package org.avenga.tests.books;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.avenga.base.BaseTest;
import org.avenga.models.request.BookRequest;
import org.avenga.models.response.BookResponse;
import org.avenga.reporting.ExtentListener;
import org.avenga.services.Books;
import org.avenga.utils.DataUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Listeners(ExtentListener.class)
public class TS_003_PostBooks extends BaseTest {

    protected Books books;

    @BeforeClass
    public void beforeClass() throws IOException {
        books = new Books();
    }

    private final BookRequest request = new BookRequest();


    @Test(testName = "[TC_017_Books] Create valid book", description = "Validate that after sending request valid payload the response status code is 201 and response body is not empty", groups = { "smoke" }, priority = 0)
    public void TC_017_CreateValidBook() throws Exception {
        books.POST_CreateBook(201, request.buildRandomBookPayload()).then().body("size()", greaterThan(0));;
    }


    @Test(testName = "[TC_018_Books] Existing ID", description = "Validate that after sending request with existing book ID the response status code is 200", groups = { "smoke" }, priority = 1)
    public void TC_018_ExistingId() throws Exception {
        books.POST_CreateBook(200, request.buildRandomBookPayload()).then().body("size()", greaterThan(0));;
    }


    @Test(testName = "[TC_019_Books] Missing title", description = "Validate that after sending request with missing title the response status code is 400", groups = { "smoke" }, priority = 2)
    public void TC_019_MissingTitle() throws Exception {
        JsonObject body = request.buildBookPayload(1, null, "", 25, "", DataUtils.getDate());
        books.POST_CreateBook(400, body);
    }


    @Test(testName = "[TC_020_Books] Missing description", description = "Validate that after sending request with missing description the response status code is 400", groups = { "smoke" }, priority = 3)
    public void TC_020_MissingDescription() throws Exception {
        JsonObject body = request.buildBookPayload(1, "title", null, 25, "", DataUtils.getDate());
        books.POST_CreateBook(400, body);
    }


    @Test(testName = "[TC_021_Books] Page count = 0", description = "Validate that after sending request with 0 as page count value the response status code is 400", groups = { "smoke" }, priority = 4)
    public void TC_021_InvalidPageCount() throws Exception {
        JsonObject body = request.buildBookPayload(1, "title", "description", 0, "", DataUtils.getDate());
        books.POST_CreateBook(400, body);
    }


    @Test(testName = "[TC_022_Books] Large title", description = "Validate that after sending request with large title the response status code is 201", groups = { "smoke" }, priority = 5)
    public void TC_022_LargeTitle() throws Exception {
        JsonObject body = request.buildBookPayload(1, "API TESTING & QUALITY CONTROL FRAMEWORK", "description", 25, "", DataUtils.getDate());
        books.POST_CreateBook(201, body);
    }


    @Test(testName = "[TC_023_Books] Invalid date", description = "Validate that after sending request with incorrect date the response status code is 400", groups = { "smoke" }, priority = 6)
    public void TC_023_InvalidDate() throws Exception {
        JsonObject body = request.buildBookPayload(1, "title", "description", 25, "", "1974-07-22T07:39:48.480Z");
        books.POST_CreateBook(400, body);
    }


    @Test(testName = "[TC_024_Books] Empty JSON", description = "Validate that after sending request with incorrect date the response status code is 400", groups = { "smoke" }, priority = 7)
    public void TC_024_EmptyJson() throws Exception {
        JsonObject body = new JsonObject();
        books.POST_CreateBook(400, body);
    }


    @Test(testName = "[TC_025_Books] Duplicated ID", description = "Validate that after sending request with already existing ID the response status code is 409", groups = { "smoke" }, priority = 8)
    public void TC_025_DuplicatedId() throws Exception {
        JsonObject body = new JsonObject();
        books.POST_CreateBook(400, body);
    }
}
