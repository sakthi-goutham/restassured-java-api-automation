package tests;

import api.base.BaseTest;
import api.endpoints.ProductsAPI;
import api.pojos.request.ProductRequest;
import api.pojos.response.ProductResponse;
import api.utils.JsonUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for Products API endpoints
 * Contains comprehensive tests for CRUD operations on products
 */
@Epic("E-Commerce API")
@Feature("Products Management")
public class ProductsTests extends BaseTest {

    @Test(priority = 1, description = "Verify getting all products returns 200 and valid response")
    @Story("Get All Products")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that GET /products returns all products with status code 200")
    public void testGetAllProducts() {
        Response response = ProductsAPI.getAllProducts();
        
        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
        
        ProductResponse[] products = response.as(ProductResponse[].class);
        
        assertThat(products)
                .as("Products array should not be empty")
                .isNotEmpty();
        
        assertThat(products[0].getId())
                .as("First product should have an ID")
                .isNotNull();
    }

    @Test(priority = 2, description = "Verify getting a single product by ID returns 200 and correct product")
    @Story("Get Product By ID")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that GET /products/{id} returns the correct product")
    public void testGetProductById() {
        int productId = 1;
        Response response = ProductsAPI.getProductById(productId);
        
        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
        
        ProductResponse product = JsonUtils.fromResponse(response, ProductResponse.class);
        
        assertThat(product.getId())
                .as("Product ID should match requested ID")
                .isEqualTo(productId);
        
        assertThat(product.getTitle())
                .as("Product title should not be null")
                .isNotNull();
        
        assertThat(product.getPrice())
                .as("Product price should be greater than 0")
                .isGreaterThan(0.0);
    }

    @Test(priority = 3, description = "Verify creating a new product returns 200 and product with ID")
    @Story("Create Product")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that POST /products creates a new product")
    public void testCreateProduct() {
        ProductRequest newProduct = ProductRequest.builder()
                .title("Test Product")
                .price(29.99)
                .description("This is a test product")
                .category("electronics")
                .image("https://i.pravatar.cc/300")
                .build();
        
        Response response = ProductsAPI.createProduct(newProduct);

        assertThat(response.getStatusCode())
                .as("Status code should be 200 or 201")
                .isIn(200, 201);
        
        ProductResponse createdProduct = JsonUtils.fromResponse(response, ProductResponse.class);
        
        assertThat(createdProduct.getId())
                .as("Created product should have an ID")
                .isNotNull();
        
        assertThat(createdProduct.getTitle())
                .as("Created product title should match request")
                .isEqualTo(newProduct.getTitle());
    }

    @Test(priority = 4, description = "Verify updating a product returns 200 and updated data")
    @Story("Update Product")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that PUT /products/{id} updates the product")
    public void testUpdateProduct() {
        int productId = 1;
        ProductRequest updateProduct = ProductRequest.builder()
                .title("Updated Product Title")
                .price(99.99)
                .description("Updated description")
                .category("electronics")
                .image("https://i.pravatar.cc/300")
                .build();
        
        Response response = ProductsAPI.updateProduct(productId, updateProduct);
        
        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
        
        ProductResponse updatedProduct = JsonUtils.fromResponse(response, ProductResponse.class);
        
        assertThat(updatedProduct.getTitle())
                .as("Updated product title should match request")
                .isEqualTo(updateProduct.getTitle());
    }

    @Test(priority = 5, description = "Verify deleting a product returns 200")
    @Story("Delete Product")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that DELETE /products/{id} deletes the product")
    public void testDeleteProduct() {
        int productId = 1;
        Response response = ProductsAPI.deleteProduct(productId);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
    }

    @Test(priority = 6, description = "Verify getting all product categories returns 200")
    @Story("Get Product Categories")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that GET /products/categories returns all categories")
    public void testGetAllCategories() {
        Response response = ProductsAPI.getAllCategories();

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);

        String[] categories = response.as(String[].class);

        assertThat(categories)
                .as("Categories array should not be empty")
                .isNotEmpty();
    }

    @Test(priority = 7, description = "Verify getting products by category returns 200 and filtered products")
    @Story("Get Products By Category")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that GET /products/category/{category} returns products in that category")
    public void testGetProductsByCategory() {
        String category = "electronics";
        Response response = ProductsAPI.getProductsByCategory(category);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);

        ProductResponse[] products = response.as(ProductResponse[].class);

        assertThat(products)
                .as("Products array should not be empty")
                .isNotEmpty();

        assertThat(products[0].getCategory())
                .as("Product category should match requested category")
                .isEqualTo(category);
    }

    @Test(priority = 8, description = "Verify getting limited products returns correct number")
    @Story("Get Limited Products")
    @Severity(SeverityLevel.MINOR)
    @Description("Test to verify that GET /products?limit={n} returns limited products")
    public void testGetLimitedProducts() {
        int limit = 5;
        Response response = ProductsAPI.getLimitedProducts(limit);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);

        ProductResponse[] products = response.as(ProductResponse[].class);

        assertThat(products.length)
                .as("Number of products should match limit")
                .isEqualTo(limit);
    }

    @Test(priority = 9, description = "Verify getting sorted products returns 200")
    @Story("Get Sorted Products")
    @Severity(SeverityLevel.MINOR)
    @Description("Test to verify that GET /products?sort={order} returns sorted products")
    public void testGetSortedProducts() {
        String sortOrder = "desc";
        Response response = ProductsAPI.getSortedProducts(sortOrder);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);

        ProductResponse[] products = response.as(ProductResponse[].class);

        assertThat(products)
                .as("Products array should not be empty")
                .isNotEmpty();
    }

    @Test(priority = 10, description = "Verify partially updating a product returns 200")
    @Story("Patch Product")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that PATCH /products/{id} partially updates the product")
    public void testPatchProduct() {
        int productId = 1;
        ProductRequest patchProduct = ProductRequest.builder()
                .title("Patched Product Title")
                .price(149.99)
                .build();

        Response response = ProductsAPI.patchProduct(productId, patchProduct);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);

        ProductResponse updatedProduct = JsonUtils.fromResponse(response, ProductResponse.class);

        assertThat(updatedProduct.getTitle())
                .as("Patched product title should match request")
                .isEqualTo(patchProduct.getTitle());
    }
}

