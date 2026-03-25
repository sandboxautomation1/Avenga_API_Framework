package org.avenga.models.books;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.avenga.utils.RandomUtils;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Book {

    // GETTERS & SETTERS

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private int pageCount;

    @Setter
    @Getter
    private String excerpt;

    @Setter
    @Getter
    private String publishDate;

    public Book() {
    }


    public Book(int id, String title, String description, int pageCount, String excerpt, String publishDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.excerpt = excerpt;
        this.publishDate = publishDate;
    }


    private static final Faker faker = new Faker();

    public static JsonObject createBookPayload(int id, String title, String description, int pageCount, String excerpt, String publishDate) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("title", title);
        body.addProperty("description", description);
        body.addProperty("pageCount", pageCount);
        body.addProperty("excerpt", excerpt);
        body.addProperty("publishDate", publishDate);
        return body;
    }

    public static JsonObject createRandomBookPayload() {
        JsonObject body = new JsonObject();
        body.addProperty("id", faker.number().numberBetween(1000, 9999));
        body.addProperty("title", faker.book().title());
        body.addProperty("description", faker.lorem().sentence());
        body.addProperty("pageCount", faker.number().numberBetween(50, 500));
        body.addProperty("excerpt", faker.lorem().paragraph());
        body.addProperty("publishDate", RandomUtils.getDate());
        return body;
    }
}