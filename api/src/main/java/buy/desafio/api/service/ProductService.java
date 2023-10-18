package buy.desafio.api.service;

import buy.desafio.api.domain.product.Product;
import buy.desafio.api.domain.user.User;
import buy.desafio.api.dto.ProductListDataDTO;
import buy.desafio.api.dto.ProductRegisterDTO;
import buy.desafio.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "product", key = "#id")
    public Product getReferenceById(Long id){
        System.out.println("Got Product. Should be cached");

        return repository.findById(id).get();
    }

    @CacheEvict(value = "product", key = "#id")
    public void remove(Long id){
        repository.deleteById(id);
    }

    public Product UserMadePurchase(Long id, User user){
        var prod = repository.getReferenceById(id);

        prod.addUser(user);

        return prod;
    }

    @CachePut(value = "product", key = "#id")
    public Product update(Long id){
        var prod = repository.getReferenceById(id);

        repository.save(prod);

        return prod;
    }

    @CacheEvict(value = {"product", "user"}, allEntries = true)
    public void cleanCache(){}

}
