package org.avenga.tests.authors;

import com.google.gson.JsonObject;
import org.avenga.base.BaseTest;
import org.avenga.models.request.AuthorRequest;
import org.avenga.models.request.BookRequest;
import org.avenga.models.response.AuthorResponse;
import org.avenga.models.response.BookResponse;
import org.avenga.reporting.ExtentListener;
import org.avenga.services.Authors;
import org.avenga.services.Books;
import org.avenga.utils.DataUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThan;


@Listeners(ExtentListener.class)
public class TS_004_PutAuthor extends BaseTest {

    private final Authors authors = new Authors();
    private final AuthorRequest request = new AuthorRequest();


    @Test(testName = "[TC_020_Authors] Valid update author", description = "Validate that a book record can be successfully updated with response code 200", groups = { "smoke" }, priority = 0)
    public void TC_020_ValidUpdate() throws Exception {
        JsonObject body = request.buildAuthorPayload(1, 1, DataUtils.getFirstName(), DataUtils.getLastName());
        authors.PUT_UpdateAuthorById(200, body, 1).then().body("size()", greaterThan(0));;
    }


    @Test(testName = "[TC_021_Authors] Update non existing", description = "Validate that a non existing author record can't be updated with response code 404", groups = { "smoke" }, priority = 1)
    public void TC_021_UpdateNonExisting() throws Exception {
        JsonObject body = request.buildAuthorPayload(99999, 99999, DataUtils.getFirstName(), DataUtils.getLastName());
        authors.PUT_UpdateAuthorById(404, body, 99999);
    }


    @Test(testName = "[TC_022_Authors] Update with invalid book ID", description = "Validate that updating records with null field will return response code 400", groups = { "smoke" }, priority = 2)
    public void TC_022_UpdateWithInvalidBookId() throws Exception {
        JsonObject body = request.buildAuthorPayload(1,-1, DataUtils.getFirstName(), DataUtils.getLastName());
        authors.PUT_UpdateAuthorById(400, body, 1);
    }
}
