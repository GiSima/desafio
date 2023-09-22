package buy.desafio.api.dto;

import buy.desafio.api.domain.product.Product;

public record ProductRegisterDetailsDTO(Long id, String name, String desc, Double price) {

    public ProductRegisterDetailsDTO(Product product){
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
