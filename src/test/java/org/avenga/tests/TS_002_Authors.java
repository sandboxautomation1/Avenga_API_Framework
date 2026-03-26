package org.avenga.tests;

import com.google.gson.JsonObject;
import org.avenga.base.BaseTest;
import org.avenga.config.ExtentListener;
import org.avenga.models.request.AuthorRequest;
import org.avenga.models.response.AuthorResponse;
import org.avenga.services.Authors;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@Listeners(ExtentListener.class)
public class TS_002_Authors extends BaseTest {

   Authors  authors =  new Authors();
   AuthorRequest  request = new AuthorRequest();
   AuthorResponse response;


    @Test(testName = "[TC_001_Authors] Retrieve a list of all authors", description = "Ensure the API returns accurate HTTP status codes for valid GET request.", groups = { "smoke" }, priority = 0)
    public void TC_001_GetAllAuthors() throws Exception {
        authors.GET_AllAuthors(200).then().body("size()", greaterThan(0));
    }


    @Test(testName = "[TC_002_Authors] Retrieve details of a specific author by its ID", description = "Validate that READ operation work as expected with valid ID.", groups = { "smoke", "acceptance" }, priority = 1)
    public void TC_002_GetAuthorByID() throws Exception {
        response = authors.GET_AuthorById(200, 1).then().extract().as(AuthorResponse.class);

        assertThat(response.getId(), greaterThan(0));
        assertThat(response.getIdBook(), notNullValue());
        assertThat(response.getFirstName(), notNullValue());
        assertThat(response.getLastName(), notNullValue());
    }


    @Test(testName = "[TC_003_Authors] Add a new author to the system", description = "Validate that CREATE operation work as expected with valid payload.", groups = { "smoke", "acceptance" }, priority = 2)
    public void TC_003_AddAuthor() throws Exception {
        response = authors.POST_AddAuthor(200, request.buildRandomAuthorPayload()).then().extract().as(AuthorResponse.class);

        assertThat(response.getId(), greaterThan(0));
        assertThat(response.getIdBook(), notNullValue());
        assertThat(response.getFirstName(), notNullValue());
        assertThat(response.getLastName(), notNullValue());
    }


    @Test(testName = "[TC_004_Authors] Update an existing author’s details by its ID", description = "Validate that UPDATE operation work as expected with valid payload.", groups = { "smoke", "acceptance" }, priority = 3)
    public void TC_004_UpdateAuthor() throws Exception {
        response = authors.PUT_UpdateAuthorById(200, request.buildRandomAuthorPayload(), "1").then().extract().as(AuthorResponse.class);

        assertThat(response.getId(), greaterThan(0));
        assertThat(response.getIdBook(), notNullValue());
        assertThat(response.getFirstName(), notNullValue());
        assertThat(response.getLastName(), notNullValue());
    }


    @Test(testName = "[TC_005_Authors] Delete a author by its ID", description = "Validate that DELETE operation work as expected with valid ID.", groups = { "smoke", "acceptance" }, priority = 4)
    public void TC_005_DeleteAuthor() throws Exception {
        authors.DELETE_DeleteAuthorById(200, "1");
    }


    @Test(testName = "[TC_006_Authors] Retrieve a author with invalid ID", description = "Validate that READ operation work as expected with invalid ID.", groups = { "regression" }, priority = 5)
    public void TC_006_GetAuthorWithInvalidId() throws Exception {
        authors.GET_AuthorById(404, 6011);
    }


    @Test(testName = "[TC_007_Authors] Add a new author with invalid book ID", description = "Validate that CREATE operation work as expected with invalid payload.", groups = { "regression" }, priority = 6)
    public void TC_007_AddAuthorWithInvalidPayload() throws Exception {
        JsonObject body = request.buildAuthorPayload(1, null, "", "");
        authors.POST_AddAuthor(400, body);
    }


    @Test(testName = "[TC_008_Authors] Update an existing author’s details with invalid book ID", description = "Validate that UPDATE operation work as expected with invalid payload.", groups = { "regression" }, priority = 7)
    public void TC_008_UpdateAuthorWithInvalidPayload() throws Exception {
        JsonObject body = request.buildAuthorPayload(1, 1, "", "");
        authors.PUT_UpdateAuthorById(405, body, "");
    }


    @Test(testName = "[TC_009_Authors] Delete non existing author", description = "Validate that DELETE operation work as expected with invalid ID.", groups = { "regression" }, priority = 8)
    public void TC_009_DeleteNonExistingAuthor() throws Exception {
        authors.DELETE_DeleteAuthorById(405, "");
    }
}
