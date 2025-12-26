package api.pojos.wrappers;

import api.pojos.module.ProductModule;
import api.pojos.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Product Wrapper POJO
 * Wrapper for handling collections and additional metadata
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductWrapper {
    
    private List<ProductModule> products;
    private Integer totalCount;
    private String category;
    
    /**
     * Create wrapper from list of ProductResponse
     * @param responses List of ProductResponse
     * @return ProductWrapper object
     */
    public static ProductWrapper fromResponseList(List<ProductResponse> responses) {
        List<ProductModule> modules = responses.stream()
                .map(ProductModule::fromResponse)
                .collect(Collectors.toList());
        
        return ProductWrapper.builder()
                .products(modules)
                .totalCount(modules.size())
                .build();
    }
    
    /**
     * Filter products by category
     * @param category Category to filter
     * @return Filtered ProductWrapper
     */
    public ProductWrapper filterByCategory(String category) {
        List<ProductModule> filtered = products.stream()
                .filter(p -> p.getProductCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        
        return ProductWrapper.builder()
                .products(filtered)
                .totalCount(filtered.size())
                .category(category)
                .build();
    }
    
    /**
     * Get products above a certain price
     * @param minPrice Minimum price
     * @return Filtered ProductWrapper
     */
    public ProductWrapper filterByMinPrice(Double minPrice) {
        List<ProductModule> filtered = products.stream()
                .filter(p -> p.getProductPrice() >= minPrice)
                .collect(Collectors.toList());
        
        return ProductWrapper.builder()
                .products(filtered)
                .totalCount(filtered.size())
                .build();
    }
    
    /**
     * Get highly rated products
     * @return Filtered ProductWrapper
     */
    public ProductWrapper getHighlyRatedProducts() {
        List<ProductModule> filtered = products.stream()
                .filter(ProductModule::isHighlyRated)
                .collect(Collectors.toList());
        
        return ProductWrapper.builder()
                .products(filtered)
                .totalCount(filtered.size())
                .build();
    }
}

