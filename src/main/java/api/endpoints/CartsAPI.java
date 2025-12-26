package api.endpoints;

import api.pojos.request.CartRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Carts API endpoint class
 * Encapsulates all REST calls for cart operations
 * 
 */
public class CartsAPI {

    private static final String CARTS_ENDPOINT = "/carts";
    private static final String CART_BY_ID_ENDPOINT = "/carts/{id}";
    private static final String USER_CARTS_ENDPOINT = "/carts/user/{userId}";

    /**
     * Get all carts
     * @return Response
     */
    @Step("Get all carts")
    public static Response getAllCarts() {
        return given()
                .when()
                .get(CARTS_ENDPOINT);
    }

    /**
     * Get cart by ID
     * @param cartId Cart ID
     * @return Response
     */
    @Step("Get cart by ID: {cartId}")
    public static Response getCartById(int cartId) {
        return given()
                .pathParam("id", cartId)
                .when()
                .get(CART_BY_ID_ENDPOINT);
    }

    /**
     * Get carts by user ID
     * @param userId User ID
     * @return Response
     */
    @Step("Get carts for user ID: {userId}")
    public static Response getCartsByUserId(int userId) {
        return given()
                .pathParam("userId", userId)
                .when()
                .get(USER_CARTS_ENDPOINT);
    }

    /**
     * Create a new cart
     * @param cartRequest Cart request body
     * @return Response
     */
    @Step("Create new cart")
    public static Response createCart(CartRequest cartRequest) {
        return given()
                .contentType("application/json")
                .body(cartRequest)
                .when()
                .post(CARTS_ENDPOINT);
    }

    /**
     * Update a cart
     * @param cartId Cart ID
     * @param cartRequest Cart request body
     * @return Response
     */
    @Step("Update cart with ID: {cartId}")
    public static Response updateCart(int cartId, CartRequest cartRequest) {
        return given()
                .contentType("application/json")
                .pathParam("id", cartId)
                .body(cartRequest)
                .when()
                .put(CART_BY_ID_ENDPOINT);
    }

    /**
     * Delete a cart
     * @param cartId Cart ID
     * @return Response
     */
    @Step("Delete cart with ID: {cartId}")
    public static Response deleteCart(int cartId) {
        return given()
                .pathParam("id", cartId)
                .when()
                .delete(CART_BY_ID_ENDPOINT);
    }

    /**
     * Get carts within date range
     * @param startDate Start date
     * @param endDate End date
     * @return Response
     */
    @Step("Get carts between {startDate} and {endDate}")
    public static Response getCartsByDateRange(String startDate, String endDate) {
        return given()
                .queryParam("startdate", startDate)
                .queryParam("enddate", endDate)
                .when()
                .get(CARTS_ENDPOINT);
    }

    /**
     * Get limited carts
     * @param limit Number of carts to return
     * @return Response
     */
    @Step("Get limited carts: {limit}")
    public static Response getLimitedCarts(int limit) {
        return given()
                .queryParam("limit", limit)
                .when()
                .get(CARTS_ENDPOINT);
    }

    /**
     * Get sorted carts
     * @param sortOrder Sort order (asc or desc)
     * @return Response
     */
    @Step("Get sorted carts: {sortOrder}")
    public static Response getSortedCarts(String sortOrder) {
        return given()
                .queryParam("sort", sortOrder)
                .when()
                .get(CARTS_ENDPOINT);
    }
}

