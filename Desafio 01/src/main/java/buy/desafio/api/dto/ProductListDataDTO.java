package buy.desafio.api.dto;

import buy.desafio.api.domain.product.Product;

public record ProductListDataDTO(Long id, String name, String description, Double price) {

    public ProductListDataDTO(Product product){
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice());

    }
}
