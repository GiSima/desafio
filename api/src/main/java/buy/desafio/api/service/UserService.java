package buy.desafio.api.service;

import buy.desafio.api.dto.UserListDataDTO;
import buy.desafio.api.dto.UserPurchaseProductDTO;
import com.dto.UserRegisterDTO;
import com.dto.UserUpdateBalanceDTO;
import com.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User create(UserRegisterDTO data){
        User user = new User(data);

        repository.save(user);

        return user;
    }

    @Cacheable(value = "user", key = "#id")
    public User getReferenceById(Long id){
        System.out.println("Got User. Should be cached");
        return repository.findById(id).get();
    }

    public Page<UserListDataDTO> list(Pageable pageable){
        return repository.findAll(pageable).map(UserListDataDTO::new);
    }

    @CachePut(value = "user", key = "#data.id")
    public User deposit(UserUpdateBalanceDTO data){
        var user = repository.getReferenceById(data.id());

        user.deposit(data);

        return user;
    }

    @CachePut(value = "user", key = "#data.id")
    public User purchase(UserPurchaseProductDTO data) {
        var user = repository.getReferenceById(data.id());

        user.purchase(data.price(), data.product());

        return user;
    }


    public void update(Long id){
        repository.save(getReferenceById(id));
    }
}
