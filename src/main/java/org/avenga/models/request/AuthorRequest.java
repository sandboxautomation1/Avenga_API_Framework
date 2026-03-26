package org.avenga.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class AuthorRequest {

    // GETTERS & SETTERS

    private int id;
    private int idBook;
    private String firstName;
    private String lastName;

    public AuthorRequest() {
    }

    private final Faker faker = new Faker();


    public JsonObject buildAuthorPayload(int id, Integer idBook, String firstName, String lastName) {
        JsonObject body = new JsonObject();
        body.addProperty("firstName", firstName);
        body.addProperty("lastName", lastName);
        body.addProperty("id", id);
        body.addProperty("idBook", idBook);
        return body;
    }

    public JsonObject buildRandomAuthorPayload() {
        JsonObject body = new JsonObject();
        body.addProperty("firstName", faker.name().firstName());
        body.addProperty("lastName", faker.name().lastName());
        body.addProperty("id", faker.number().numberBetween(1, 100));
        body.addProperty("idBook", faker.number().numberBetween(1, 100));
        return body;
    }


}