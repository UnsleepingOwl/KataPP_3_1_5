package ru.unsleepingowl.katasecurity.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.unsleepingowl.katasecurity.service.ServiceImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;

    private ServiceImp serviceImp;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, ServiceImp serviceImp) {
        this.successUserHandler = successUserHandler;
        this.serviceImp = serviceImp;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authenticationProvider(getDaoAuthenticationProvider())
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/auth/").permitAll()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .permitAll();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return serviceImp.getPASSWORD_ENCODER();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        authenticationProvider.setUserDetailsService(serviceImp);
        return authenticationProvider;
    }
}