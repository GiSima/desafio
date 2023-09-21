package buy.desafio.api.controller;

import buy.desafio.api.dto.ShopBuyDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @PostMapping
    public void buy(@RequestBody @Valid ShopBuyDTO data){
//        double amount = data.amount() * ProductController.priceById(data.idProduct());
//        UserController.purchase(data.idUser(), data.amount());
    }
}
