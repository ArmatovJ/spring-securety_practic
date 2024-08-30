package kg.end_pont.springsecurety_practic.dto;


import java.util.List;

public class UserDto {
    private  Long id;
    private  String username;
    private  String email;
    private  List<String> roles;

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserDto setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}