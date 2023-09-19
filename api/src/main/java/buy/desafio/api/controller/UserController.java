package buy.desafio.api.controller;

import buy.desafio.api.user.DadosCadastroUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroUser json){
        System.out.println(json);
    }

}
