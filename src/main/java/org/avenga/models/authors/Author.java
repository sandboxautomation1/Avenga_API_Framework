package org.avenga.models.authors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Author {

    // GETTERS & SETTERS
    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int idBook;

    @Setter
    @Getter
    private String firstName;

    @Setter
    @Getter
    private String lastName;

    public Author() {
    }


    public Author(int id, int idBook, String firstName, String lastName) {
        this.id = id;
        this.idBook = idBook;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    private static final Faker faker = new Faker();

    public static JsonObject createAuthorPayload(int id, Integer idBook, String firstName, String lastName) {
        JsonObject body = new JsonObject();
        body.addProperty("firstName", firstName);
        body.addProperty("lastName", lastName);
        body.addProperty("id", id);
        body.addProperty("idBook", idBook);
        return body;
    }

    public static JsonObject createRandomAuthorPayload() {
        JsonObject body = new JsonObject();
        body.addProperty("firstName", faker.name().firstName());
        body.addProperty("lastName", faker.name().lastName());
        body.addProperty("id", faker.number().numberBetween(1, 100));
        body.addProperty("idBook", faker.number().numberBetween(1, 100));
        return body;
    }


}