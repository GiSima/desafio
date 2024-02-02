package buy.desafio.api.dto;

import com.domain.product.Product;

public record UserPurchaseProductDTO(
        Long id,
        Double price,
        Product product) {
}
