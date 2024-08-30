package kg.end_pont.springsecurety_practic.configuration;

import kg.end_pont.springsecurety_practic.security.JwtAuthEntryPoint;
import kg.end_pont.springsecurety_practic.security.JitAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private final JwtAuthEntryPoint authEntryPoint;
    private final JitAuthenticationFilter jitAuthenticationFilter;

    @Autowired
    public SecurityConfiguration(JwtAuthEntryPoint authEntryPoint,
                                 JitAuthenticationFilter jitAuthenticationFilter) {
        this.authEntryPoint = authEntryPoint;
        this.jitAuthenticationFilter = jitAuthenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();

        http.exceptionHandling().authenticationEntryPoint(authEntryPoint);
        http.addFilterBefore(jitAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        http.httpBasic()
                .and().authorizeRequests()
                .antMatchers("/example/other").permitAll()
                .antMatchers("/auth/log-in").permitAll()

                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
