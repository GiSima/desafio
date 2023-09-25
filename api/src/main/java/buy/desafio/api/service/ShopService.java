package buy.desafio.api.service;

import buy.desafio.api.dto.BuyProductDTO;
import buy.desafio.api.dto.PurchaseProductDTO;
import buy.desafio.api.repository.ProductRepository;
import buy.desafio.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public PurchaseProductDTO buyProduct(BuyProductDTO data){
        var user = userService.getReferenceById(data.userId());
        var prod = productService.getReferenceById(data.productId());

        double price = data.amount() * prod.getPrice();

        userService.purchase(data.userId(), price, prod);
        productService.UserMadePurchase(data.productId(), user);

        userService.update(data.userId());
        productService.update(data.productId());

        return new PurchaseProductDTO(user.getName(), prod.getName(), prod.getPrice(), data.amount(), price);
    }

}
