package project.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import project.spring.model.entities.UserDetailsImpl;
import project.spring.model.service.UserServiceImpl;

@EnableWebSecurity
public class MyWebSecurityAdapter extends WebSecurityConfigurerAdapter {



    private final UserDetailsService userDetailsService;

    @Autowired
    public MyWebSecurityAdapter(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/library").authenticated()
                .and().httpBasic()
                .and().formLogin()
                .successHandler((req, resp, auth) -> resp.sendRedirect("/library"))
                .loginPage("/login").usernameParameter("login").passwordParameter("password").permitAll();
    }
}
