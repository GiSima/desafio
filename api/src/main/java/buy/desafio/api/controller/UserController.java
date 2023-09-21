package buy.desafio.api.controller;

import buy.desafio.api.domain.User;
import buy.desafio.api.dto.UserListDataDTO;
import buy.desafio.api.dto.UserRegisterDTO;
import buy.desafio.api.dto.UserUpdateBalanceDTO;
import buy.desafio.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid UserRegisterDTO data){
        repository.save(new User(data));
    }

    @GetMapping
    public Page<UserListDataDTO> list(@PageableDefault(size = 10, sort = {"name", "cpf"}) Pageable pageable){
        return repository.findAll(pageable).map(UserListDataDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UserUpdateBalanceDTO data){
        var user = repository.getReferenceById(data.id());
        user.deposit(data);
    }

    @Transactional
    public void purchase(Long id, double amount){
        var user = repository.getReferenceById(id);
        user.decreaseBalance(amount);
    }
}
