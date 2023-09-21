package buy.desafio.api.controller;

import buy.desafio.api.domain.Product;
import buy.desafio.api.dto.ProductRegisterDTO;
import buy.desafio.api.dto.ProductListDataDTO;
import buy.desafio.api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid ProductRegisterDTO data){
        repository.save(new Product(data));
    }

    @GetMapping
    public Page<ProductListDataDTO> list(@PageableDefault(size = 50) Pageable pageable){
        return repository.findAll(pageable).map(ProductListDataDTO::new);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remove(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public double priceById(Long id){
        return repository.getReferenceById(id).getPrice();
    }
}
