package kg.end_pont.springsecurety_practic.controller;


import kg.end_pont.springsecurety_practic.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserService authService;

    @Autowired
    public AuthController(AuthUserService authService) {
        this.authService = authService;
    }

    @PostMapping("/log-in")
    public String getUserToken(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password
    ){
        return this.authService.userLogin(login, password);
    }

}
