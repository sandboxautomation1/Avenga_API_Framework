package org.avenga.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorResponse {

    private int id;
    private int idBook;
    private String firstName;
    private String lastName;
}