package org.avenga.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.avenga.client.BaseClient;
import org.avenga.reporting.WriterOutputStream;
import org.avenga.data.Endpoints;

import java.io.IOException;

import static org.avenga.reporting.WriterOutputStream.writeRequestLog;
import static org.avenga.reporting.WriterOutputStream.writeResponseLog;


public class Users {


    public Response GET_Users(int expectedStatusCode) throws IOException {
        Response response = (Response) RestAssured
                .given()
                .spec(BaseClient.getRequestSpecification(Endpoints.USERS))
                .when()
                .get()
                .then()
                .spec(BaseClient.getResponseSpecification(expectedStatusCode));

        WriterOutputStream.setRequestLog(writeRequestLog());
        WriterOutputStream.setResponseLog(writeResponseLog());

        return response;
    }
}
