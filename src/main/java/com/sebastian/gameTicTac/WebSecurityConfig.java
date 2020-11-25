package com.sebastian.gameTicTac;

import com.sebastian.gameTicTac.Dao.PlayerDao;
import com.sebastian.gameTicTac.Model.Player;
import com.sebastian.gameTicTac.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private PlayerService playerService;
    private PlayerDao playerDao;

    @Autowired
    public WebSecurityConfig(PlayerService playerService, PlayerDao playerDao) {
        this.playerService = playerService;
        this.playerDao = playerDao;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(playerService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //home
                .antMatchers(HttpMethod.GET, "/").permitAll()//.hasRole("ADMIN")
                //db console
                .antMatchers(HttpMethod.POST, "/console").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/console").permitAll()//hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/console").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/console").hasRole("ADMIN")
                //player api
                .antMatchers(HttpMethod.GET, "/player/id/{id}").permitAll()//.hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/player/name/{userName}").permitAll()//.hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/player").permitAll()
                .antMatchers(HttpMethod.GET, "/player").permitAll()//hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/player").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/player").hasRole("ADMIN")
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll().and()
                .csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addPredefinedAdminUser(){
        //admin player object
        Player admin = new Player("adminPlayer", passwordEncoder().encode("adminPassword"),
                0, 0, 0, 0, 0, "ADMIN");
       // playerDao.addPlayer(admin);
    }
}
