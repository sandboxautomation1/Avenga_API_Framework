package org.avenga.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import lombok.Data;
import org.avenga.utils.DataUtils;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class BookRequest {

    // GETTERS & SETTERS

    private int id;
    private String title;
    private String description;
    private int pageCount;
    private String excerpt;
    private String publishDate;

    public BookRequest() {
    }

    private final Faker faker = new Faker();


    public JsonObject buildBookPayload(int id, String title, String description, int pageCount, String excerpt, String publishDate) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("title", title);
        body.addProperty("description", description);
        body.addProperty("pageCount", pageCount);
        body.addProperty("excerpt", excerpt);
        body.addProperty("publishDate", publishDate);
        return body;
    }

    public JsonObject buildRandomBookPayload() {
        JsonObject body = new JsonObject();
        body.addProperty("id", faker.number().numberBetween(1000, 9999));
        body.addProperty("title", faker.book().title());
        body.addProperty("description", faker.lorem().sentence());
        body.addProperty("pageCount", faker.number().numberBetween(50, 500));
        body.addProperty("excerpt", faker.lorem().paragraph());
        body.addProperty("publishDate", DataUtils.getDate());
        return body;
    }
}