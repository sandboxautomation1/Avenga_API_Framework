package org.avenga.tests;

import com.google.gson.JsonObject;
import org.avenga.base.BaseTest;
import org.avenga.config.ExtentListener;
import org.avenga.models.authors.Author;
import org.avenga.models.authors.AuthorBuilder;
import org.avenga.services.Authors;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentListener.class)
public class TS_002_Authors extends BaseTest {

   Authors authors =  new Authors();


    @Test(testName = "[TC_001_Authors] Retrieve a list of all authors", description = "Retrieve a list of all authors by sending correct request", groups = {"GET"}, priority = 0)
    public void TC_001_GetAllAuthors() throws Exception {
        authors.GET_AllAuthors(200);
    }


    @Test(testName = "[TC_002_Authors] Retrieve details of a specific author by its ID", description = "GET", priority = 1)
    public void TC_002_GetAuthorByID() throws Exception {
        authors.GET_AuthorById(200, 1);
    }


    @Test(testName = "[TC_003_Authors] Add a new author to the system", description = "POST", priority = 2)
    public void TC_003_AddAuthor() throws Exception {
        authors.POST_AddAuthor(200, Author.createRandomAuthorPayload());
    }


    @Test(testName = "[TC_004_Authors] Update an existing author’s details by its ID", description = "PUT", priority = 3)
    public void TC_004_UpdateAuthor() throws Exception {
        authors.PUT_UpdateAuthorById(200, Author.createRandomAuthorPayload(), 1);
    }


    @Test(testName = "[TC_005_Authors] Delete a author by its ID", description = "DELETE", priority = 4)
    public void TC_005_DeleteAuthor() throws Exception {
        authors.DELETE_DeleteAuthorById(200, 1);
    }


    @Test(testName = "[TC_006_Authors] Retrieve a author with invalid ID", description = "GET", priority = 5)
    public void TC_006_GetAuthorWithInvalidId() throws Exception {
        authors.GET_AuthorById(400, null);
    }


    @Test(testName = "[TC_007_Authors] Add a new author with invalid book ID", description = "POST", priority = 6)
    public void TC_007_AddAuthorWithInvalidPayload() throws Exception {
        JsonObject body = Author.createAuthorPayload(1, null, "", "");
        authors.POST_AddAuthor(400, body);
    }


    @Test(testName = "[TC_008_Authors] Update an existing author’s details with invalid book ID", description = "PUT", priority = 7)
    public void TC_008_UpdateAuthorWithInvalidPayload() throws Exception {
        JsonObject body = Author.createAuthorPayload(1, 1, "", "");
        authors.PUT_UpdateAuthorById(400, body, null);
    }


    @Test(testName = "[TC_009_Authors] Delete non existing author", description = "DELETE",  priority = 8)
    public void TC_009_DeleteNonExistingAuthor() throws Exception {
        authors.DELETE_DeleteAuthorById(400, null);
    }
}
