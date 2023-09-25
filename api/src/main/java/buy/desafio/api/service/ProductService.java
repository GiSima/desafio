package buy.desafio.api.service;

import buy.desafio.api.domain.product.Product;
import buy.desafio.api.domain.user.User;
import buy.desafio.api.dto.ProductListDataDTO;
import buy.desafio.api.dto.ProductRegisterDTO;
import buy.desafio.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product create(ProductRegisterDTO data){
        var product = new Product(data);

        repository.save(product);

        return product;
    }

    public Page<ProductListDataDTO> list(Pageable pageable){
        return repository.findAll(pageable).map(ProductListDataDTO::new);
    }

    public void remove(Long id){
        repository.deleteById(id);
    }

    public Product getReferenceById(Long id){
        return repository.getReferenceById(id);
    }

    public void UserMadePurchase(Long id, User user){
        repository.getReferenceById(id).addUser(user);
    }

    public void update(Long id){
        repository.save(getReferenceById(id));
    }

}
