package buy.desafio.api.service;

import buy.desafio.api.domain.user.User;
import buy.desafio.api.dto.UserListDataDTO;
import buy.desafio.api.dto.UserRegisterDTO;
import buy.desafio.api.dto.UserUpdateBalanceDTO;
import buy.desafio.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User create(UserRegisterDTO data){
        User user = new User(data);

        repository.save(user);

        return user;
    }

    public User getReferenceById(Long id){
        return repository.getReferenceById(id);
    }

    public Page<UserListDataDTO> list(Pageable pageable){
        return repository.findAll(pageable).map(UserListDataDTO::new);
    }

    public User deposit(UserUpdateBalanceDTO data){
        var user = repository.getReferenceById(data.id());

        user.deposit(data);

        return user;
    }
}
