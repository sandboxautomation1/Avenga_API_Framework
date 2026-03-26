package org.avenga.services;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.avenga.client.BaseClient;
import org.avenga.reporting.WriterOutputStream;
import org.avenga.data.Endpoints;
import org.avenga.utils.JsonUtils;

import java.io.IOException;

import static org.avenga.reporting.WriterOutputStream.writeRequestLog;
import static org.avenga.reporting.WriterOutputStream.writeResponseLog;

public class Authors {


    /** Retrieve a list of all authors */
    public Response GET_AllAuthors(int expectedStatusCode) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.AUTHORS))
                .when()
                .get();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }


    /** Retrieve details of a specific author by their ID */
    public Response GET_AuthorById(int expectedStatusCode, Integer id) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.AUTHORS_BY_ID))
                .pathParam("id", id)
                .when()
                .get();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }


    /** Update an existing author’s details */
    public Response PUT_UpdateAuthorById(int expectedStatusCode, JsonObject body, String authorId) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.AUTHORS_BY_ID))
                .pathParam("id", authorId)
                .body(JsonUtils.convertToJsonNode(body))
                .when()
                .put();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }


    /** Delete an author by their ID */
    public Response DELETE_DeleteAuthorById(int expectedStatusCode, String authorId) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.AUTHORS_BY_ID))
                .pathParam("id", authorId)
                .when()
                .delete();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }


    /** Add new author record to the system */
    public Response POST_AddAuthor(int expectedStatusCode, JsonObject body) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.AUTHORS))
                .body(JsonUtils.convertToJsonNode(body))
                .when()
                .post();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }


    /** Retrieve a list of all books by book ID */
    public Response GET_AllAuthorBooksByBookId(int expectedStatusCode, int bookId) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.AUTHORS_BOOKS ))
                .pathParam("bookId", bookId)
                .when()
                .get();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }
}
