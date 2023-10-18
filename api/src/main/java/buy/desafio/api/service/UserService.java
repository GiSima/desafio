package buy.desafio.api.service;

import buy.desafio.api.domain.product.Product;
import buy.desafio.api.domain.user.User;
import buy.desafio.api.dto.UserListDataDTO;
import buy.desafio.api.dto.UserPurchaseProductDTO;
import buy.desafio.api.dto.UserRegisterDTO;
import buy.desafio.api.dto.UserUpdateBalanceDTO;
import buy.desafio.api.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
