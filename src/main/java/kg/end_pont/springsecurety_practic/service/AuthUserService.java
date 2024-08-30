package kg.end_pont.springsecurety_practic.service;

import kg.end_pont.springsecurety_practic.security.JwtHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtHandler jwtHandler;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthUserService(
            PasswordEncoder passwordEncoder,
            JwtHandler jwtHandler,
            UserDetailsService userDetailsService
    )
    {
        this.passwordEncoder = passwordEncoder;
        this.jwtHandler = jwtHandler;
        this.userDetailsService = userDetailsService;
    }

    public String userLogin(String login, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        System.out.println(password);
        System.out.println(userDetails.getPassword());
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            Authentication authentication =
                    UsernamePasswordAuthenticationToken
                            .authenticated(userDetails, null, userDetails.getAuthorities());
            System.out.println(jwtHandler.jwtGenerationToken(authentication));

            return jwtHandler.jwtGenerationToken(authentication);
        }
        return "Error token";
    }
    public String encodeUserPassword(String password) {
        return passwordEncoder.encode(password);
    }


}
