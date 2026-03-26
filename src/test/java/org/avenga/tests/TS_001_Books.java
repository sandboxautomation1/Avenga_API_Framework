package org.avenga.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import org.avenga.base.BaseTest;
import org.avenga.config.ExtentListener;
import org.avenga.models.request.BookRequest;
import org.avenga.models.response.BookResponse;
import org.avenga.services.Books;
import org.avenga.utils.JsonUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Listeners(ExtentListener.class)
public class TS_001_Books extends BaseTest {

    private Books books = new Books();
    private final BookRequest request = new BookRequest();
    private BookResponse response;


    @Test(testName = "[TC_001_Books] Retrieve a list of all books", description = "Ensure the API returns accurate HTTP status codes for valid GET request.", groups = {"GET"}, priority = 0)
    public void TC_001_GetAllBooks() throws Exception {
        books.GET_AllBooks(200).then().body("size()", greaterThan(0));;
    }


    @Test(testName = "[TC_002_Books] Retrieve details of a specific book by its ID", description = "Validate that READ operation work as expected with valid ID.", groups = {"GET"}, priority = 1)
    public void TC_002_GetBookByID() throws Exception {
        response = books.GET_BookById(200, 1).then().extract().as(BookResponse.class);

        assertThat(response.getId(), is(1));
        assertThat(response.getTitle(), notNullValue());
        assertThat(response.getDescription(), notNullValue());
        assertThat(response.getExcerpt(), notNullValue());
        assertThat(response.getPageCount(), greaterThan(0));
        assertThat(response.getPublishDate(), notNullValue());
    }


    @Test(testName = "[TC_003_Books] Add a new book to the system", description = "Validate that CREATE operation work as expected with valid payload.", groups = {"POST"}, priority = 2)
    public void TC_003_AddBook() throws Exception {
        response = books.POST_CreateBook(200, request.buildRandomBookPayload()).then().extract().as(BookResponse.class);

        assertThat(response.getId(), greaterThan(0));
        assertThat(response.getTitle(), notNullValue());
        assertThat(response.getDescription(), notNullValue());
        assertThat(response.getExcerpt(), notNullValue());
        assertThat(response.getPageCount(), greaterThan(0));
        assertThat(response.getPublishDate(), notNullValue());
    }


    @Test(testName = "[TC_004_Books] Update an existing book by its ID", description = "Validate that UPDATE operation work as expected with valid payload.", groups = {"PUT"}, priority = 3)
    public void TC_004_UpdateBook() throws Exception {
        response = books.PUT_UpdateBookById(200, request.buildRandomBookPayload(), 1).then().extract().as(BookResponse.class);

        assertThat(response.getId(), greaterThan(0));
        assertThat(response.getTitle(), notNullValue());
        assertThat(response.getDescription(), notNullValue());
        assertThat(response.getExcerpt(), notNullValue());
        assertThat(response.getPageCount(), greaterThan(0));
        assertThat(response.getPublishDate(), notNullValue());
    }


    @Test(testName = "[TC_005_Books] Delete a book by its ID", description = "Validate that DELETE operation work as expected with valid ID.", groups = {"DELETE"}, priority = 4)
    public void TC_005_DeleteBook() throws Exception {
        books.DELETE_DeleteBookById(200, "1");
    }


    @Test(testName = "[TC_006_Books] Retrieve a book with invalid ID", description = "Validate that READ operation work as expected with invalid ID.", groups = {"GET"}, priority = 5)
    public void TC_006_GetBookWithInvalidId() throws Exception {
        books.GET_BookById(404, 201);
    }


    @Test(testName = "[TC_007_Books] Add a new book to the system with missing publishing date", description = "Validate that CREATE operation work as expected with invalid payload.", groups = {"POST"}, priority = 6)
    public void TC_007_AddBookWithInvalidPayload() throws Exception {
        JsonObject body = request.buildBookPayload(1, "Title,", "Description", 25, "", null);
        books.POST_CreateBook(400, body);
    }


    @Test(testName = "[TC_008_Books] Update book with missing publishing date", description = "Validate that UPDATE operation work as expected with invalid payload.", groups = {"PUT"}, priority = 7)
    public void TC_008_UpdateBookWithInvalidPayload() throws Exception {
        JsonObject body = request.buildBookPayload(1, "Title,", "Description", 25, "", null);
        books.PUT_UpdateBookById(400, body, 1);
    }


    @Test(testName = "[TC_009_Books] Delete non existing record", description = "Validate that DELETE operation work as expected with invalid payload.", groups = {"DELETE"}, priority = 8)
    public void TC_009_DeleteNonExistingBook() throws Exception {
        books.DELETE_DeleteBookById(405, "");
    }
}
