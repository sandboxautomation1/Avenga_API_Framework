package org.avenga.services;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.avenga.client.BaseClient;
import org.avenga.config.WriterOutputStream;
import org.avenga.data.Endpoints;
import org.avenga.models.books.Book;
import org.avenga.utils.JsonUtils;

import java.io.IOException;

import static org.avenga.config.WriterOutputStream.writeRequestLog;
import static org.avenga.config.WriterOutputStream.writeResponseLog;

public class Books {

    /** Retrieve a list of all books */
    public Response GET_AllBooks(int expectedStatusCode) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.BOOKS))
                .when()
                .get();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }


    /** Retrieve details of a specific book by its ID */
    public Response GET_BookById(int expectedStatusCode, int id) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.BOOKS + "/" + id))
                .when()
                .get();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }


    /** Update an existing book by its ID */
    public Response PUT_UpdateBookById(int expectedStatusCode, JsonObject body, int id) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.BOOKS + "/" + id))
                .body(JsonUtils.convertToJsonNode(body))
                .when()
                .put();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }


    /** Delete a book by its ID */
    public Response DELETE_DeleteBookById(int expectedStatusCode, Integer id) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.BOOKS + "/" + id))
                .when()
                .delete();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }


    /** Add a new book to the system */
    public Response POST_CreateBook(int expectedStatusCode, JsonObject body) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.BOOKS))
                .body(JsonUtils.convertToJsonNode(body))
                .when()
                .post();

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        response.then().spec(BaseClient.getResponseSpecification(expectedStatusCode));

        return response;
    }
}
