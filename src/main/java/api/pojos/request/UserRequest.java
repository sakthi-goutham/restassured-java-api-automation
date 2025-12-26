package api.pojos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Request POJO
 * Used for creating and updating users
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {
    
    private String email;
    private String username;
    private String password;
    private NameRequest name;
    private AddressRequest address;
    private String phone;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class NameRequest {
        private String firstname;
        private String lastname;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AddressRequest {
        private String city;
        private String street;
        private Integer number;
        private String zipcode;
        private GeolocationRequest geolocation;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class GeolocationRequest {
        private String lat;
        private String lng;
    }
}

