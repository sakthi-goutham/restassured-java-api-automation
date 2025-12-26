package api.pojos.module;

import api.pojos.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product Module POJO
 * Business logic layer for product data
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModule {
    
    private Integer productId;
    private String productTitle;
    private Double productPrice;
    private String productDescription;
    private String productCategory;
    private String productImage;
    private Double ratingRate;
    private Integer ratingCount;
    
    /**
     * Convert ProductResponse to ProductModule
     * @param response ProductResponse object
     * @return ProductModule object
     */
    public static ProductModule fromResponse(ProductResponse response) {
        return ProductModule.builder()
                .productId(response.getId())
                .productTitle(response.getTitle())
                .productPrice(response.getPrice())
                .productDescription(response.getDescription())
                .productCategory(response.getCategory())
                .productImage(response.getImage())
                .ratingRate(response.getRating() != null ? response.getRating().getRate() : null)
                .ratingCount(response.getRating() != null ? response.getRating().getCount() : null)
                .build();
    }
    
    /**
     * Check if product is in stock based on rating count
     * @return true if in stock
     */
    public boolean isInStock() {
        return ratingCount != null && ratingCount > 0;
    }
    
    /**
     * Check if product is highly rated
     * @return true if rating is above 4.0
     */
    public boolean isHighlyRated() {
        return ratingRate != null && ratingRate >= 4.0;
    }
}

