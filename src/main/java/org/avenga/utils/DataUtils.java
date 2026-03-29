package org.avenga.utils;

import com.github.javafaker.Faker;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtils {

    private final static Faker faker = new Faker();


    // ==========================
    // Data
    // ==========================

    public static int getNumber() {
        return faker.number().numberBetween(1, 9999);
    }

    public static String getTitleString() {
        return faker.book().title();
    }

    public static String getString() {
        return faker.lorem().paragraph();
    }

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }



    // ==========================
    // Date
    // ==========================

    public static String getDate() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");
        return now.format(formatter);
    }

    public static void validateISO8601(String date) {
        try {
            OffsetDateTime.parse(date);
        } catch (DateTimeParseException e) {
            throw new AssertionError("Invalid date format: " + date);
        }
    }
}
