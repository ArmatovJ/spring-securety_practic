package kg.end_pont.springsecurety_practic.controller;


import kg.end_pont.springsecurety_practic.dto.UserDto;
import kg.end_pont.springsecurety_practic.entity.UserEntity;
import kg.end_pont.springsecurety_practic.mappers.UserMapper;
import kg.end_pont.springsecurety_practic.service.AuthUserService;
import kg.end_pont.springsecurety_practic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final AuthUserService authUserService;

    @Autowired
    public UserController(UserService service, AuthUserService authUserService) {
        this.service = service;
        this.authUserService = authUserService;
    }

    @GetMapping("/other")
    public String someOtherMethod() {
        return "Other method";
    }

    @GetMapping("/get_all_users")
    public List<UserDto> getAllUsers() {
        return UserMapper.toDtoList(service.getAllEntities());
    }

    @GetMapping("/get_user_by_id")
    public UserDto getUserById(@RequestParam("id") Long id) {
        return UserMapper.toDto(service.getEntityById(id));
    }

    @GetMapping("/get_users_by_login")
    public List<UserDto> getUserByLogin(@RequestParam("login") String login) {
        return UserMapper.toDtoList(service.getEntitiesByName(login));
    }

    @Secured(value = {"ADMIN"})
    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @DeleteMapping("/remove_user")
    public void removeUser(@RequestParam(name = "id") Long id){
        service.deleteEntityById(id);
    }


    @PostMapping("/register_new_user")
    public void registerUser(@RequestBody UserEntity user){
        String password = user.getPassword();
        String encodePassword = authUserService.encodeUserPassword(password);
        user.setPassword(encodePassword);
        service.registerNewEntity(user);
    }
}
