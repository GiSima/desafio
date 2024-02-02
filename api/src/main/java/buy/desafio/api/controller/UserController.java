package buy.desafio.api.controller;

import buy.desafio.api.dto.BuyProductDTO;
import buy.desafio.api.dto.PurchaseProductDTO;
import buy.desafio.api.dto.UserListDataDTO;
import buy.desafio.api.service.ShopService;
import buy.desafio.api.service.UserService;
import com.dto.UserRegisterDTO;
import com.dto.UserUpdateBalanceDTO;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserRepository repository;

    @GetMapping
    public ResponseEntity<Page<UserListDataDTO>> list(
            @PageableDefault(size = 100, sort = {"name", "cpf"}) Pageable pageable){
        var page = userService.list(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserListDataDTO> detail(@PathVariable Long id){
        var user = userService.getReferenceById(id);

        return ResponseEntity.ok(new UserListDataDTO(user));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserListDataDTO> register(@RequestBody @Valid UserRegisterDTO data, UriComponentsBuilder uriBuilder){
        var user = userService.create(data);

        var uri = uriBuilder.path("users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserListDataDTO(user));
    }

    @PostMapping("/buy")
    @Transactional
    public ResponseEntity<PurchaseProductDTO> purchase(@RequestBody @Valid BuyProductDTO data){
        PurchaseProductDTO detail = shopService.buyProduct(data);

        return ResponseEntity.ok(detail);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserListDataDTO> update(@RequestBody @Valid UserUpdateBalanceDTO data){
        var user = userService.deposit(data);

        return ResponseEntity.ok(new UserListDataDTO(user));
    }

}
