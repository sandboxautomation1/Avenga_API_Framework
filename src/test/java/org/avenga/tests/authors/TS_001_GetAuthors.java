package org.avenga.tests.authors;

import io.restassured.response.Response;
import org.avenga.base.BaseTest;
import org.avenga.models.request.AuthorRequest;
import org.avenga.models.request.BookRequest;
import org.avenga.models.response.AuthorResponse;
import org.avenga.models.response.BookResponse;
import org.avenga.reporting.ExtentListener;
import org.avenga.services.Authors;
import org.avenga.services.Books;
import org.avenga.utils.DataUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Listeners(ExtentListener.class)
public class TS_001_GetAuthors extends BaseTest {

    private final Authors authors = new Authors();


    @Test(testName = "[TC_001_Authors] Get all authors", description = "Validate that response code is 200 and the response body is not empty", groups = { "smoke" }, priority = 0)
    public void TC_001_GetAllAuthors() throws Exception {
        authors.GET_AllAuthors(200).then().body("size()", greaterThan(0));;
    }


    @Test(testName = "[TC_002_Authors] Schema Validation", description = "Validate that the response code is 200 and all fields exists", groups = { "smoke", "acceptance" }, priority = 1)
    public void TC_002_SchemaValidation() throws Exception {
        AuthorResponse response = authors.GET_AllAuthors(200).then().extract().as(AuthorResponse.class);

        assertThat(response.getId(), is(1));
        assertThat(response.getFirstName(), notNullValue());
        assertThat(response.getLastName(), notNullValue());
        assertThat(response.getIdBook(), notNullValue());
    }


    @Test(testName = "[TC_003_Authors] Validate is ID unique", description = "Validate that ID is unique", groups = {"regression"}, priority = 2)
    public void TC_003_ValidateUniqueId() throws Exception {
        Response response = authors.GET_AllAuthors(200);
        List<Integer> ids = response.jsonPath().getList("id");
        Set<Integer> uniqueIds = new HashSet<>(ids);
        Assert.assertEquals(ids.size(), uniqueIds.size(), "Found duplicate IDs!");
    }


    @Test(testName = "[TC_004_Authors] Validate fist name", description = "Validate that first name is not null and response code is 200", groups = { "smoke" }, priority = 3)
    public void TC_004_ValidateFirstName() throws Exception {
        authors.GET_AllAuthors(200).then().body("firstName", notNullValue());
    }


    @Test(testName = "[TC_005_Authors] Validate last name", description = "Validate that last name is not null and response code is 200", groups = { "smoke" }, priority = 4)
    public void TC_005_ValidateLastName() throws Exception {
        authors.GET_AllAuthors(200).then().body("lastName", notNullValue());
    }


    @Test(testName = "[TC_006_Authors] Response not empty", description = "Validate that the response body is not empty", groups = { "smoke" }, priority = 5)
    public void TC_006_ResponseNotEmpty() throws Exception {
        authors.GET_AllAuthors(200).then().body("size()", greaterThan(0));;
    }

}
