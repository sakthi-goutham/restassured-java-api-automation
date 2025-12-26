package api.pojos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Cart Response POJO
 * Maps the API response for cart endpoints
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartResponse {
    
    private Integer id;
    private Integer userId;
    private String date;
    private List<ProductItem> products;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProductItem {
        private Integer productId;
        private Integer quantity;
    }
}

