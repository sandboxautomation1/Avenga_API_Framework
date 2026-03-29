package org.avenga.tests.authors;

import com.google.gson.JsonObject;
import org.avenga.base.BaseTest;
import org.avenga.models.request.AuthorRequest;
import org.avenga.models.request.BookRequest;
import org.avenga.models.response.BookResponse;
import org.avenga.reporting.ExtentListener;
import org.avenga.services.Authors;
import org.avenga.services.Books;
import org.avenga.utils.DataUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThan;


@Listeners(ExtentListener.class)
public class TS_003_PostAuthors extends BaseTest {

    private final Authors authors = new Authors();
    private final AuthorRequest request = new AuthorRequest();


    @Test(testName = "[TC_013_Authors] Create valid author", description = "Validate that after sending request valid payload the response status code is 201 and response body is not empty", groups = { "smoke" }, priority = 0)
    public void TC_013_CreateValidAuthor() throws Exception {
        authors.POST_AddAuthor(201, request.buildRandomAuthorPayload()).then().body("size()", greaterThan(0));;
    }


    @Test(testName = "[TC_014_Authors] Existing ID", description = "Validate that after sending request with existing book ID the response status code is 200", groups = { "smoke" }, priority = 1)
    public void TC_014_ExistingId() throws Exception {
        JsonObject body = request.buildAuthorPayload(1, DataUtils.getNumber(), DataUtils.getFirstName(), DataUtils.getLastName());
        authors.POST_AddAuthor(200, body);
    }


    @Test(testName = "[TC_015_Authors] Missing first name", description = "Validate that after sending request with missing first name the response status code is 400", groups = { "smoke" }, priority = 2)
    public void TC_015_MissingFirstName() throws Exception {
        JsonObject body = request.buildAuthorPayload(1, DataUtils.getNumber(), null, DataUtils.getFirstName());
        authors.POST_AddAuthor(400, body);
    }


    @Test(testName = "[TC_016_Authors] Missing last name", description = "Validate that after sending request with missing last name the response status code is 400", groups = { "smoke" }, priority = 3)
    public void TC_016_MissingLastName() throws Exception {
        JsonObject body = request.buildAuthorPayload(1, DataUtils.getNumber(), DataUtils.getFirstName(), null);
        authors.POST_AddAuthor(400, body);
    }


    @Test(testName = "[TC_017_Authors] Negative book ID", description = "Validate that after sending request with negative number as book ID the response status code is 400", groups = { "smoke" }, priority = 4)
    public void TC_017_NegativeBookId() throws Exception {
        JsonObject body = request.buildAuthorPayload(1, -1, DataUtils.getFirstName(), DataUtils.getLastName());
        authors.POST_AddAuthor(400, body);
    }


    @Test(testName = "[TC_018_Authors] Empty JSON", description = "Validate that after sending request with empty body the response status code is 400", groups = { "smoke" }, priority = 5)
    public void TC_018_EmptyJson() throws Exception {
        JsonObject body = new JsonObject();
        authors.POST_AddAuthor(400, body);
    }


    @Test(testName = "[TC_019_Authors] Missing book ID", description = "Validate that after sending request with missing book ID the response status code is 400", groups = { "smoke" }, priority = 6)
    public void TC_019_MissingBookId() throws Exception {
        JsonObject body = request.buildAuthorPayload(DataUtils.getNumber(), null, DataUtils.getFirstName(), DataUtils.getLastName());
        authors.POST_AddAuthor(400, body);
    }
}
