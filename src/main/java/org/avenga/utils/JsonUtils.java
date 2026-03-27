package org.avenga.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();
    private static Gson gson = new Gson();


    // ==========================
    // RestAssured
    // ==========================

    public static JsonNode convertToJsonNode(JsonObject json) throws JsonProcessingException {
        return mapper.readTree(json.toString());
    }
}
