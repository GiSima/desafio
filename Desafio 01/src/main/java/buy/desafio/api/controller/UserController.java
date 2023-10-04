package buy.desafio.api.controller;

import buy.desafio.api.dto.*;
import buy.desafio.api.service.ShopService;
import buy.desafio.api.service.UserService;
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

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO data, UriComponentsBuilder uriBuilder){
        var user = userService.create(data);

        var uri = uriBuilder.path("users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserListDataDTO(user));
    }

    @PostMapping("/buy")
    @Transactional
    public ResponseEntity purchase(@RequestBody @Valid BuyProductDTO data){
        PurchaseProductDTO detail = shopService.buyProduct(data);

        return ResponseEntity.ok(detail);
    }


    @GetMapping
    public ResponseEntity<Page<UserListDataDTO>> list(@PageableDefault(size = 10, sort = {"name", "cpf"}) Pageable pageable){
        var page = userService.list(pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UserUpdateBalanceDTO data){
        var user = userService.deposit(data);

        return ResponseEntity.ok(new UserListDataDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var user = userService.getReferenceById(id);

        return ResponseEntity.ok(new UserListDataDTO(user));
    }

}
