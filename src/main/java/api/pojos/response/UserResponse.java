package api.pojos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Response POJO
 * Maps the API response for user endpoints
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    
    private Integer id;
    private String email;
    private String username;
    private String password;
    private NameResponse name;
    private AddressResponse address;
    private String phone;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NameResponse {
        private String firstname;
        private String lastname;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AddressResponse {
        private String city;
        private String street;
        private Integer number;
        private String zipcode;
        private GeolocationResponse geolocation;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeolocationResponse {
        private String lat;
        private String lng;
    }
}

