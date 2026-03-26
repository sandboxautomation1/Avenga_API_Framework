package org.avenga.data;

public class Endpoints {

    /** BASIC */
    public static final String BASE_URL = "https://fakerestapi.azurewebsites.net";


    /** AUTHORS */
    public static final String AUTHORS       = "/api/v1/Authors";
    public static final String AUTHORS_BY_ID = "/api/v1/Authors/{id}";
    public static final String AUTHORS_BOOKS = "/api/v1/Authors/authors/books/{bookId}";


    /** BOOKS */
    public static final String BOOKS         = "/api/v1/Books";
    public static final String BOOKS_BY_ID   = "/api/v1/Books/{id}";


    /** USERS */
    public static final String USERS         = "/api/v1/Users";
}
