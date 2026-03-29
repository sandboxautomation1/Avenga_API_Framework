package org.avenga.tests.books;

import com.google.gson.JsonObject;
import org.avenga.base.BaseTest;
import org.avenga.models.request.BookRequest;
import org.avenga.models.response.BookResponse;
import org.avenga.reporting.ExtentListener;
import org.avenga.services.Books;
import org.avenga.utils.DataUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThan;


@Listeners(ExtentListener.class)
public class TS_004_PutBook extends BaseTest {

    private final Books books = new Books();
    private final BookRequest request = new BookRequest();


    @Test(testName = "[TC_026_Books] Valid update book", description = "Validate that a book record can be successfully updated with response code 200", groups = { "smoke" }, priority = 0)
    public void TC_026_ValidUpdate() throws Exception {
        JsonObject body = request.buildBookPayload(1, DataUtils.getTitleString(), DataUtils.getString(), DataUtils.getNumber(), DataUtils.getString(), DataUtils.getDate());
        books.PUT_UpdateBookById(200, body, 1).then().body("size()", greaterThan(0));;
    }


    @Test(testName = "[TC_027_Books] Update non existing", description = "Validate that a non existing book record can't be updated with response code 404", groups = { "smoke" }, priority = 1)
    public void TC_027_UpdateNonExisting() throws Exception {
        JsonObject body = request.buildBookPayload(9999, DataUtils.getTitleString(), DataUtils.getString(), DataUtils.getNumber(), DataUtils.getString(), DataUtils.getDate());
        books.PUT_UpdateBookById(404, body, 9999);
    }


    @Test(testName = "[TC_028_Books] Update with null fields", description = "Validate that updating records with null field will return response code 400", groups = { "smoke" }, priority = 1)
    public void TC_028_UpdateWithNullFields() throws Exception {
        JsonObject body = request.buildBookPayload(1, DataUtils.getTitleString(), null, DataUtils.getNumber(), DataUtils.getString(), DataUtils.getDate());
        books.PUT_UpdateBookById(400, body, 1);
    }


    @Test(testName = "[TC_029_Books] Update with long title", description = "Validate that a book record can be updated with long title and the response code is 200", groups = { "smoke" }, priority = 1)
    public void TC_029_UpdateWithLongTitle() throws Exception {
        JsonObject body = request.buildBookPayload(1, "API TESTING & QUALITY CONTROL FRAMEWORK", DataUtils.getString(), DataUtils.getNumber(), DataUtils.getString(), DataUtils.getDate());
        books.PUT_UpdateBookById(200, body, 1);
    }


    @Test(testName = "[TC_030_Books] Update with invalid pageCount", description = "Validate that after sending request with invalid pageCount returned response code is 400", groups = { "smoke" }, priority = 1)
    public void TC_030_UpdateWithInvalidPageCount() throws Exception {
        JsonObject body = request.buildBookPayload(1, DataUtils.getTitleString(), DataUtils.getString(), -1, DataUtils.getString(), DataUtils.getDate());
        books.PUT_UpdateBookById(400, body, 1);
    }
}
