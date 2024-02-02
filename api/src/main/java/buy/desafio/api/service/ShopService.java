package buy.desafio.api.service;

import buy.desafio.api.dto.BuyProductDTO;
import buy.desafio.api.dto.PurchaseProductDTO;
import buy.desafio.api.dto.UserPurchaseProductDTO;
import buy.desafio.api.infra.PreconditionFailedException;
import buy.desafio.api.infra.PurchaseException;
import buy.desafio.api.infra.PurchaseNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ShopService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public PurchaseProductDTO buyProduct(BuyProductDTO data){
        var user = userService.getReferenceById(data.userId());
        var prod = productService.getReferenceById(data.productId());
        var response = "";

        try {
            response = restTemplate.getForObject("http://172.31.230.177:8080/chame-aqui/" + user.getName(), String.class);
        } catch (HttpClientErrorException e){
            throw new PurchaseException("Error when attempting to purchase product.");
        }

        switch (response) {
            case "Permitido":
                double price = data.amount() * prod.getPrice();

                userService.purchase(new UserPurchaseProductDTO(data.userId(), price, prod));
                productService.UserMadePurchase(data.productId(), user);

                userService.update(data.userId());
                productService.update(data.productId());

                return new PurchaseProductDTO(user.getName(), prod.getName(), prod.getPrice(), data.amount(), price);
            case "Negado":
                throw new PurchaseNotAllowedException("You are not allowed to purchase at this moment.");
            default:
                throw new PreconditionFailedException("Couldn't get response from authorization service.");
        }
    }

    // telnet 172.31.230.177 22
}
