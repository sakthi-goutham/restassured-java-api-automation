package api.pojos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product Response POJO
 * Maps the API response for product endpoints
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {
    
    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private RatingResponse rating;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RatingResponse {
        private Double rate;
        private Integer count;
    }
}

