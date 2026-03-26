package org.avenga.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.avenga.utils.RandomUtils;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {

    private int id;
    private String title;
    private String description;
    private int pageCount;
    private String excerpt;
    private String publishDate;
}