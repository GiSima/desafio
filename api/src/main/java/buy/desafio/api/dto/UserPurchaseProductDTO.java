package buy.desafio.api.dto;

import buy.desafio.api.domain.product.Product;

public record UserPurchaseProductDTO(
        Long id,
        Double price,
        Product product) {
}
