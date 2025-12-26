package tests;

import api.base.BaseTest;
import api.endpoints.CartsAPI;
import api.pojos.request.CartRequest;
import api.pojos.response.CartResponse;
import api.utils.JsonUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for Carts API endpoints
 * Contains comprehensive tests for CRUD operations on carts
 */
@Epic("E-Commerce API")
@Feature("Carts Management")
public class CartsTests extends BaseTest {

    @Test(priority = 1, description = "Verify getting all carts returns 200 and valid response")
    @Story("Get All Carts")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that GET /carts returns all carts with status code 200")
    public void testGetAllCarts() {
        Response response = CartsAPI.getAllCarts();
        
        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
        
        CartResponse[] carts = response.as(CartResponse[].class);
        
        assertThat(carts)
                .as("Carts array should not be empty")
                .isNotEmpty();
        
        assertThat(carts[0].getId())
                .as("First cart should have an ID")
                .isNotNull();
    }

    @Test(priority = 2, description = "Verify getting a single cart by ID returns 200 and correct cart")
    @Story("Get Cart By ID")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that GET /carts/{id} returns the correct cart")
    public void testGetCartById() {
        int cartId = 1;
        Response response = CartsAPI.getCartById(cartId);
        
        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
        
        CartResponse cart = JsonUtils.fromResponse(response, CartResponse.class);
        
        assertThat(cart.getId())
                .as("Cart ID should match requested ID")
                .isEqualTo(cartId);
        
        assertThat(cart.getUserId())
                .as("Cart should have a user ID")
                .isNotNull();
        
        assertThat(cart.getProducts())
                .as("Cart should have products")
                .isNotEmpty();
    }

    @Test(priority = 3, description = "Verify getting carts by user ID returns 200 and user's carts")
    @Story("Get Carts By User ID")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that GET /carts/user/{userId} returns carts for that user")
    public void testGetCartsByUserId() {
        int userId = 1;
        Response response = CartsAPI.getCartsByUserId(userId);
        
        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
        
        CartResponse[] carts = response.as(CartResponse[].class);
        
        assertThat(carts)
                .as("Carts array should not be empty")
                .isNotEmpty();
        
        assertThat(carts[0].getUserId())
                .as("Cart user ID should match requested user ID")
                .isEqualTo(userId);
    }

    @Test(priority = 4, description = "Verify creating a new cart returns 200 and cart with ID")
    @Story("Create Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that POST /carts creates a new cart")
    public void testCreateCart() {
        CartRequest newCart = CartRequest.builder()
                .userId(1)
                .date(LocalDate.now().toString())
                .products(Arrays.asList(
                        CartRequest.ProductItem.builder()
                                .productId(1)
                                .quantity(2)
                                .build(),
                        CartRequest.ProductItem.builder()
                                .productId(2)
                                .quantity(1)
                                .build()
                ))
                .build();
        
        Response response = CartsAPI.createCart(newCart);

        assertThat(response.getStatusCode())
                .as("Status code should be 200 or 201")
                .isIn(200, 201);
        
        CartResponse createdCart = JsonUtils.fromResponse(response, CartResponse.class);
        
        assertThat(createdCart.getId())
                .as("Created cart should have an ID")
                .isNotNull();
    }

    @Test(priority = 5, description = "Verify updating a cart returns 200 and updated data")
    @Story("Update Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that PUT /carts/{id} updates the cart")
    public void testUpdateCart() {
        int cartId = 1;
        CartRequest updateCart = CartRequest.builder()
                .userId(1)
                .date(LocalDate.now().toString())
                .products(Arrays.asList(
                        CartRequest.ProductItem.builder()
                                .productId(3)
                                .quantity(5)
                                .build()
                ))
                .build();
        
        Response response = CartsAPI.updateCart(cartId, updateCart);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
    }

    @Test(priority = 6, description = "Verify deleting a cart returns 200")
    @Story("Delete Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that DELETE /carts/{id} deletes the cart")
    public void testDeleteCart() {
        int cartId = 1;
        Response response = CartsAPI.deleteCart(cartId);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
    }

    @Test(priority = 7, description = "Verify getting limited carts returns correct number")
    @Story("Get Limited Carts")
    @Severity(SeverityLevel.MINOR)
    @Description("Test to verify that GET /carts?limit={n} returns limited carts")
    public void testGetLimitedCarts() {
        int limit = 3;
        Response response = CartsAPI.getLimitedCarts(limit);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);

        CartResponse[] carts = response.as(CartResponse[].class);

        assertThat(carts.length)
                .as("Number of carts should match limit")
                .isEqualTo(limit);
    }

    @Test(priority = 8, description = "Verify getting sorted carts returns 200")
    @Story("Get Sorted Carts")
    @Severity(SeverityLevel.MINOR)
    @Description("Test to verify that GET /carts?sort={order} returns sorted carts")
    public void testGetSortedCarts() {
        String sortOrder = "desc";
        Response response = CartsAPI.getSortedCarts(sortOrder);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);

        CartResponse[] carts = response.as(CartResponse[].class);

        assertThat(carts)
                .as("Carts array should not be empty")
                .isNotEmpty();
    }

    @Test(priority = 9, description = "Verify getting carts by date range returns 200")
    @Story("Get Carts By Date Range")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that GET /carts?startdate={start}&enddate={end} returns carts in date range")
    public void testGetCartsByDateRange() {
        String startDate = "2019-12-10";
        String endDate = "2020-10-10";
        Response response = CartsAPI.getCartsByDateRange(startDate, endDate);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);

        CartResponse[] carts = response.as(CartResponse[].class);

        assertThat(carts)
                .as("Carts array should not be null")
                .isNotNull();
    }

    @Test(priority = 10, description = "Verify cart response contains all required fields")
    @Story("Validate Cart Response Schema")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that cart response contains all required fields")
    public void testCartResponseSchema() {
        int cartId = 1;
        Response response = CartsAPI.getCartById(cartId);

        CartResponse cart = JsonUtils.fromResponse(response, CartResponse.class);

        assertThat(cart.getId()).as("Cart ID should not be null").isNotNull();
        assertThat(cart.getUserId()).as("User ID should not be null").isNotNull();
        assertThat(cart.getDate()).as("Date should not be null").isNotNull();
        assertThat(cart.getProducts()).as("Products should not be null").isNotNull();
        assertThat(cart.getProducts()).as("Products should not be empty").isNotEmpty();
        assertThat(cart.getProducts().get(0).getProductId()).as("Product ID should not be null").isNotNull();
        assertThat(cart.getProducts().get(0).getQuantity()).as("Quantity should not be null").isNotNull();
    }
}

