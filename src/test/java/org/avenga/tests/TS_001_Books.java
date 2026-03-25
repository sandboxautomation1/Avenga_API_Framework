package org.avenga.tests;

import com.google.gson.JsonObject;
import org.avenga.base.BaseTest;
import org.avenga.config.ExtentListener;
import org.avenga.models.books.Book;
import org.avenga.models.books.BookBuilder;
import org.avenga.services.Books;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentListener.class)
public class TS_001_Books extends BaseTest {

    Books books = new Books();


    @Test(testName = "[TC_001_Books] Retrieve a list of all books", description = "GET", priority = 0)
    public void TC_001_GetAllBooks() throws Exception {
        books.GET_AllBooks(200);
    }


    @Test(testName = "[TC_002_Books] Retrieve details of a specific book by its ID", description = "GET", priority = 1)
    public void TC_002_GetBookByID() throws Exception {
        books.GET_BookById(200, 1);
    }


    @Test(testName = "[TC_003_Books] Add a new book to the system", description = "POST", priority = 2)
    public void TC_003_AddBook() throws Exception {
        books.POST_CreateBook(200, Book.createRandomBookPayload());
    }


    @Test(testName = "[TC_004_Books] Update an existing book by its ID", description = "PUT", priority = 3)
    public void TC_004_UpdateBook() throws Exception {
        books.PUT_UpdateBookById(200, Book.createRandomBookPayload(), 1);
    }


    @Test(testName = "[TC_005_Books] Delete a book by its ID", description = "DELETE", priority = 4)
    public void TC_005_DeleteBook() throws Exception {
        books.DELETE_DeleteBookById(200, 1);
    }


    @Test(testName = "[TC_006_Books] Retrieve a book with invalid ID", description = "GET", priority = 5)
    public void TC_006_GetBookWithInvalidId() throws Exception {
        books.GET_BookById(404, 201);
    }


    @Test(testName = "[TC_007_Books] Add a new book to the system with missing publishing date", description = "POST", priority = 6)
    public void TC_007_AddBookWithInvalidPayload() throws Exception {
        JsonObject body = Book.createBookPayload(1, "Title,", "Description", 25, "", null);
        books.POST_CreateBook(400, body);
    }


    @Test(testName = "[TC_008_Books] Update book with missing publishing date", description = "PUT", priority = 7)
    public void TC_008_UpdateBookWithInvalidPayload() throws Exception {
        JsonObject body = Book.createBookPayload(1, "Title,", "Description", 25, "", null);
        books.PUT_UpdateBookById(400, body, 1);
    }


    @Test(testName = "[TC_009_Books] Delete non existing record", description = "DELETE", priority = 8)
    public void TC_009_DeleteNonExistingBook() throws Exception {
        books.DELETE_DeleteBookById(400, null);
    }
}
