package buy.desafio.api.service;

import buy.desafio.api.dto.BuyProductDTO;
import buy.desafio.api.repository.ProductRepository;
import buy.desafio.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public void buyProduct(BuyProductDTO data){
        var user = userRepository.getReferenceById(data.userId());
        var prod = productRepository.getReferenceById(data.productId());
        double price = data.amount() * prod.getPrice();
        user.purchase(price);
    }

}
