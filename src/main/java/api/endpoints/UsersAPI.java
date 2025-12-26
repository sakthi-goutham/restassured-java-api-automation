package api.endpoints;

import api.pojos.request.UserRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Users API endpoint class
 * Encapsulates all REST calls for user operations
 * 
 */
public class UsersAPI {

    private static final String USERS_ENDPOINT = "/users";
    private static final String USER_BY_ID_ENDPOINT = "/users/{id}";

    /**
     * Get all users
     * @return Response
     */
    @Step("Get all users")
    public static Response getAllUsers() {
        return given()
                .when()
                .get(USERS_ENDPOINT);
    }

    /**
     * Get user by ID
     * @param userId User ID
     * @return Response
     */
    @Step("Get user by ID: {userId}")
    public static Response getUserById(int userId) {
        return given()
                .pathParam("id", userId)
                .when()
                .get(USER_BY_ID_ENDPOINT);
    }

    /**
     * Create a new user
     * @param userRequest User request body
     * @return Response
     */
    @Step("Create new user")
    public static Response createUser(UserRequest userRequest) {
        return given()
                .contentType("application/json")
                .body(userRequest)
                .when()
                .post(USERS_ENDPOINT);
    }

    /**
     * Update a user
     * @param userId User ID
     * @param userRequest User request body
     * @return Response
     */
    @Step("Update user with ID: {userId}")
    public static Response updateUser(int userId, UserRequest userRequest) {
        return given()
                .contentType("application/json")
                .pathParam("id", userId)
                .body(userRequest)
                .when()
                .put(USER_BY_ID_ENDPOINT);
    }

    /**
     * Delete a user
     * @param userId User ID
     * @return Response
     */
    @Step("Delete user with ID: {userId}")
    public static Response deleteUser(int userId) {
        return given()
                .pathParam("id", userId)
                .when()
                .delete(USER_BY_ID_ENDPOINT);
    }

    /**
     * Get limited users
     * @param limit Number of users to return
     * @return Response
     */
    @Step("Get limited users: {limit}")
    public static Response getLimitedUsers(int limit) {
        return given()
                .queryParam("limit", limit)
                .when()
                .get(USERS_ENDPOINT);
    }

    /**
     * Get sorted users
     * @param sortOrder Sort order (asc or desc)
     * @return Response
     */
    @Step("Get sorted users: {sortOrder}")
    public static Response getSortedUsers(String sortOrder) {
        return given()
                .queryParam("sort", sortOrder)
                .when()
                .get(USERS_ENDPOINT);
    }
}

