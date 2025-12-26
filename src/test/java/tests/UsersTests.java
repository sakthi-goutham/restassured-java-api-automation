package tests;

import api.base.BaseTest;
import api.endpoints.UsersAPI;
import api.pojos.request.UserRequest;
import api.pojos.response.UserResponse;
import api.utils.JsonUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for Users API endpoints
 * Contains comprehensive tests for CRUD operations on users
 */
@Epic("E-Commerce API")
@Feature("Users Management")
public class UsersTests extends BaseTest {

    @Test(priority = 1, description = "Verify getting all users returns 200 and valid response")
    @Story("Get All Users")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that GET /users returns all users with status code 200")
    public void testGetAllUsers() {
        Response response = UsersAPI.getAllUsers();
        
        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
        
        UserResponse[] users = response.as(UserResponse[].class);
        
        assertThat(users)
                .as("Users array should not be empty")
                .isNotEmpty();
        
        assertThat(users[0].getId())
                .as("First user should have an ID")
                .isNotNull();
    }

    @Test(priority = 2, description = "Verify getting a single user by ID returns 200 and correct user")
    @Story("Get User By ID")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that GET /users/{id} returns the correct user")
    public void testGetUserById() {
        int userId = 1;
        Response response = UsersAPI.getUserById(userId);
        
        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
        
        UserResponse user = JsonUtils.fromResponse(response, UserResponse.class);
        
        assertThat(user.getId())
                .as("User ID should match requested ID")
                .isEqualTo(userId);
        
        assertThat(user.getEmail())
                .as("User email should not be null")
                .isNotNull();
        
        assertThat(user.getUsername())
                .as("Username should not be null")
                .isNotNull();
    }

    @Test(priority = 3, description = "Verify creating a new user returns 200 and user with ID")
    @Story("Create User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that POST /users creates a new user")
    public void testCreateUser() {
        UserRequest newUser = UserRequest.builder()
                .email("test@example.com")
                .username("testuser")
                .password("Test@123")
                .name(UserRequest.NameRequest.builder()
                        .firstname("John")
                        .lastname("Doe")
                        .build())
                .address(UserRequest.AddressRequest.builder()
                        .city("New York")
                        .street("5th Avenue")
                        .number(123)
                        .zipcode("10001")
                        .geolocation(UserRequest.GeolocationRequest.builder()
                                .lat("40.7128")
                                .lng("-74.0060")
                                .build())
                        .build())
                .phone("1-555-1234")
                .build();
        
        Response response = UsersAPI.createUser(newUser);

        assertThat(response.getStatusCode())
                .as("Status code should be 200 or 201")
                .isIn(200, 201);
        
        UserResponse createdUser = JsonUtils.fromResponse(response, UserResponse.class);
        
        assertThat(createdUser.getId())
                .as("Created user should have an ID")
                .isNotNull();
    }

    @Test(priority = 4, description = "Verify updating a user returns 200 and updated data")
    @Story("Update User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that PUT /users/{id} updates the user")
    public void testUpdateUser() {
        int userId = 1;
        UserRequest updateUser = UserRequest.builder()
                .email("updated@example.com")
                .username("updateduser")
                .password("Updated@123")
                .name(UserRequest.NameRequest.builder()
                        .firstname("Jane")
                        .lastname("Smith")
                        .build())
                .build();
        
        Response response = UsersAPI.updateUser(userId, updateUser);
        
        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
    }

    @Test(priority = 5, description = "Verify deleting a user returns 200")
    @Story("Delete User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that DELETE /users/{id} deletes the user")
    public void testDeleteUser() {
        int userId = 1;
        Response response = UsersAPI.deleteUser(userId);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);
    }

    @Test(priority = 6, description = "Verify getting limited users returns correct number")
    @Story("Get Limited Users")
    @Severity(SeverityLevel.MINOR)
    @Description("Test to verify that GET /users?limit={n} returns limited users")
    public void testGetLimitedUsers() {
        int limit = 3;
        Response response = UsersAPI.getLimitedUsers(limit);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);

        UserResponse[] users = response.as(UserResponse[].class);

        assertThat(users.length)
                .as("Number of users should match limit")
                .isEqualTo(limit);
    }

    @Test(priority = 7, description = "Verify getting sorted users returns 200")
    @Story("Get Sorted Users")
    @Severity(SeverityLevel.MINOR)
    @Description("Test to verify that GET /users?sort={order} returns sorted users")
    public void testGetSortedUsers() {
        String sortOrder = "desc";
        Response response = UsersAPI.getSortedUsers(sortOrder);

        assertThat(response.getStatusCode())
                .as("Status code should be 200")
                .isEqualTo(200);

        UserResponse[] users = response.as(UserResponse[].class);

        assertThat(users)
                .as("Users array should not be empty")
                .isNotEmpty();
    }

    @Test(priority = 8, description = "Verify user response contains all required fields")
    @Story("Validate User Response Schema")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that user response contains all required fields")
    public void testUserResponseSchema() {
        int userId = 1;
        Response response = UsersAPI.getUserById(userId);

        UserResponse user = JsonUtils.fromResponse(response, UserResponse.class);

        assertThat(user.getId()).as("User ID should not be null").isNotNull();
        assertThat(user.getEmail()).as("Email should not be null").isNotNull();
        assertThat(user.getUsername()).as("Username should not be null").isNotNull();
        assertThat(user.getName()).as("Name should not be null").isNotNull();
        assertThat(user.getName().getFirstname()).as("First name should not be null").isNotNull();
        assertThat(user.getName().getLastname()).as("Last name should not be null").isNotNull();
        assertThat(user.getAddress()).as("Address should not be null").isNotNull();
        assertThat(user.getPhone()).as("Phone should not be null").isNotNull();
    }
}

