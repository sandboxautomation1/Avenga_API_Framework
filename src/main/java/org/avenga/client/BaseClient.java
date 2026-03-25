package org.avenga.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.avenga.config.WriterOutputStream;

import static org.avenga.config.WriterOutputStream.requestCapture;
import static org.avenga.config.WriterOutputStream.responseCapture;
import static org.avenga.data.Endpoints.*;

public class BaseClient {

    // ----------------------------------------------------------------------
    // REQUEST & RESPONSE SPECIFICATION BUILDERS
    // ----------------------------------------------------------------------

    /**
     * Reusable request specification method
     *
     * @param path
     * @return new Request Specification Builder
     */
    public static RequestSpecification getRequestSpecification(String path) {
        return new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter(requestCapture))
                .addFilter(new ResponseLoggingFilter(responseCapture))
                .setBaseUri(BASE_URL)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    /**
     * Reusable response specification method
     *
     * @return new Response Specification Builder
     */
    public static ResponseSpecification getResponseSpecification(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(LogDetail.ALL)
                .build();
    }
}
