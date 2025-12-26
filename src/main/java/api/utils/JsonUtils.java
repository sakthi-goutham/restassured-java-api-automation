package api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;

/**
 * Utility class for JSON operations
 * Handles serialization and deserialization of JSON objects
 * 
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * Convert object to JSON string
     * @param object Object to convert
     * @return JSON string
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * Convert JSON string to object
     * @param json JSON string
     * @param clazz Target class
     * @param <T> Type parameter
     * @return Deserialized object
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }

    /**
     * Convert Response to object
     * @param response RestAssured Response
     * @param clazz Target class
     * @param <T> Type parameter
     * @return Deserialized object
     */
    public static <T> T fromResponse(Response response, Class<T> clazz) {
        return fromJson(response.asString(), clazz);
    }

    /**
     * Read JSON from file
     * @param filePath Path to JSON file
     * @param clazz Target class
     * @param <T> Type parameter
     * @return Deserialized object
     */
    public static <T> T fromFile(String filePath, Class<T> clazz) {
        try {
            return objectMapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from file: " + filePath, e);
        }
    }

    /**
     * Write object to JSON file
     * @param object Object to write
     * @param filePath Target file path
     */
    public static void toFile(Object object, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), object);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to file: " + filePath, e);
        }
    }

    /**
     * Pretty print JSON string
     * @param json JSON string
     * @return Formatted JSON string
     */
    public static String prettyPrint(String json) {
        try {
            Object jsonObject = objectMapper.readValue(json, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            return json;
        }
    }
}

