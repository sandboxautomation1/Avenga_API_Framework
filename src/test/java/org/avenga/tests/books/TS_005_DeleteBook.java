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
public class TS_005_DeleteBook extends BaseTest {

    private final Books books = new Books();


    @Test(testName = "[TC_031_Books] Delete existing book", description = "Validate that existing book can be successfully deleted with response code 200", groups = { "smoke" }, priority = 0)
    public void TC_031_DeleteBook() throws Exception {
        books.DELETE_DeleteBookById(200, 1);
    }


    @Test(testName = "[TC_031_Books] Delete non existing book", description = "Validate that after sending request for delete non existing book the response code is 404", groups = { "smoke" }, priority = 0)
    public void TC_031_DeleteNonExistingBook() throws Exception {
        books.DELETE_DeleteBookById(404, 99999);
    }


    @Test(testName = "[TC_032_Books] Delete already deleted book", description = "Validate that after sending request for delete already deleted book the response code is 404", groups = { "smoke" }, priority = 0)
    public void TC_032_DeleteTwice() throws Exception {
        books.DELETE_DeleteBookById(200, 1);
        books.DELETE_DeleteBookById(404, 1);
    }
}
