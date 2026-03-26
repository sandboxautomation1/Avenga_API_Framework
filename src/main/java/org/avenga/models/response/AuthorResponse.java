package org.avenga.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorResponse {

    // GETTERS
    @Getter
    private int id;

    @Getter
    private int idBook;

    @Getter
    private String firstName;

    @Getter
    private String lastName;
}