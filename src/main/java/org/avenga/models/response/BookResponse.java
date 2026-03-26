package org.avenga.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.avenga.utils.RandomUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {

    // GETTERS

    @Getter
    private int id;

    @Getter
    private String title;

    @Getter
    private String description;

    @Getter
    private int pageCount;

    @Getter
    private String excerpt;

    @Getter
    private String publishDate;
}