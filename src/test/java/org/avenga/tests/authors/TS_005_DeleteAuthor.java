package org.avenga.tests.authors;

import org.avenga.base.BaseTest;
import org.avenga.models.request.BookRequest;
import org.avenga.models.response.BookResponse;
import org.avenga.reporting.ExtentListener;
import org.avenga.services.Authors;
import org.avenga.services.Books;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(ExtentListener.class)
public class TS_005_DeleteAuthor extends BaseTest {

    private final Authors authors = new Authors();


    @Test(testName = "[TC_023_Authors] Delete existing author", description = "Validate that existing author can be successfully deleted with response code 200", groups = { "smoke" }, priority = 0)
    public void TC_023_DeleteAuthor() throws Exception {
        authors.DELETE_DeleteAuthorById(200, 1);
    }


    @Test(testName = "[TC_024_Authors] Delete non existing author", description = "Validate that after sending request for delete non existing author the response code is 404", groups = { "smoke" }, priority = 1)
    public void TC_024_DeleteNonExistingAuthor() throws Exception {
        authors.DELETE_DeleteAuthorById(404, 99999);
    }


    @Test(testName = "[TC_025_Authors] Delete already deleted author", description = "Validate that after sending request for delete already deleted author the response code is 404", groups = { "smoke" }, priority = 2)
    public void TC_032_DeleteTwice() throws Exception {
        authors.GET_AuthorById(200, 1);
        authors.DELETE_DeleteAuthorById(404, 1);
    }
}
