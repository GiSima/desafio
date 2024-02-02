package buy.desafio.api.controller;

import buy.desafio.api.dto.ProductListDataDTO;
import buy.desafio.api.service.ProductService;
import com.dto.ProductRegisterDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

//    @Autowired
//    private ProductRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid ProductRegisterDTO data, UriComponentsBuilder uriBuilder){
        var product = service.create(data);

        var uri = uriBuilder.path("products/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProductListDataDTO(product));
    }

    @GetMapping
    public ResponseEntity<Page<ProductListDataDTO>> list(@PageableDefault(size = 50) Pageable pageable){
        var page = service.list(pageable);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remove(@PathVariable Long id){
        service.remove(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var product = service.getReferenceById(id);

        return ResponseEntity.ok(new ProductListDataDTO(product));
    }

    @DeleteMapping("/cleanCache")
    public void cleanCache() {
        service.cleanCache();
    }
}
