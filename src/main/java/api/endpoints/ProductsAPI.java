package api.endpoints;

import api.pojos.request.ProductRequest;
import api.pojos.response.ProductResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Products API endpoint class
 * Encapsulates all REST calls for product operations
 * 
 */
public class ProductsAPI {

    private static final String PRODUCTS_ENDPOINT = "/products";
    private static final String PRODUCT_BY_ID_ENDPOINT = "/products/{id}";
    private static final String PRODUCTS_CATEGORIES_ENDPOINT = "/products/categories";
    private static final String PRODUCTS_BY_CATEGORY_ENDPOINT = "/products/category/{category}";

    /**
     * Get all products
     * @return Response
     */
    @Step("Get all products")
    public static Response getAllProducts() {
        return given()
                .when()
                .get(PRODUCTS_ENDPOINT);
    }

    /**
     * Get product by ID
     * @param productId Product ID
     * @return Response
     */
    @Step("Get product by ID: {productId}")
    public static Response getProductById(int productId) {
        return given()
                .pathParam("id", productId)
                .when()
                .get(PRODUCT_BY_ID_ENDPOINT);
    }

    /**
     * Create a new product
     * @param productRequest Product request body
     * @return Response
     */
    @Step("Create new product")
    public static Response createProduct(ProductRequest productRequest) {
        return given()
                .contentType("application/json")
                .body(productRequest)
                .when()
                .post(PRODUCTS_ENDPOINT);
    }

    /**
     * Update a product
     * @param productId Product ID
     * @param productRequest Product request body
     * @return Response
     */
    @Step("Update product with ID: {productId}")
    public static Response updateProduct(int productId, ProductRequest productRequest) {
        return given()
                .contentType("application/json")
                .pathParam("id", productId)
                .body(productRequest)
                .when()
                .put(PRODUCT_BY_ID_ENDPOINT);
    }

    /**
     * Partially update a product
     * @param productId Product ID
     * @param productRequest Product request body
     * @return Response
     */
    @Step("Partially update product with ID: {productId}")
    public static Response patchProduct(int productId, ProductRequest productRequest) {
        return given()
                .contentType("application/json")
                .pathParam("id", productId)
                .body(productRequest)
                .when()
                .patch(PRODUCT_BY_ID_ENDPOINT);
    }

    /**
     * Delete a product
     * @param productId Product ID
     * @return Response
     */
    @Step("Delete product with ID: {productId}")
    public static Response deleteProduct(int productId) {
        return given()
                .pathParam("id", productId)
                .when()
                .delete(PRODUCT_BY_ID_ENDPOINT);
    }

    /**
     * Get all product categories
     * @return Response
     */
    @Step("Get all product categories")
    public static Response getAllCategories() {
        return given()
                .when()
                .get(PRODUCTS_CATEGORIES_ENDPOINT);
    }

    /**
     * Get products by category
     * @param category Category name
     * @return Response
     */
    @Step("Get products in category: {category}")
    public static Response getProductsByCategory(String category) {
        return given()
                .pathParam("category", category)
                .when()
                .get(PRODUCTS_BY_CATEGORY_ENDPOINT);
    }

    /**
     * Get limited products
     * @param limit Number of products to return
     * @return Response
     */
    @Step("Get limited products: {limit}")
    public static Response getLimitedProducts(int limit) {
        return given()
                .queryParam("limit", limit)
                .when()
                .get(PRODUCTS_ENDPOINT);
    }

    /**
     * Get sorted products
     * @param sortOrder Sort order (asc or desc)
     * @return Response
     */
    @Step("Get sorted products: {sortOrder}")
    public static Response getSortedProducts(String sortOrder) {
        return given()
                .queryParam("sort", sortOrder)
                .when()
                .get(PRODUCTS_ENDPOINT);
    }
}

