package br.com.challange.videos.videos;

import br.com.challange.videos.videos.details.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/process_register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/user/home")
                        .permitAll())
                .logout().logoutSuccessUrl("/home").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/register_success").permitAll()
//                .antMatchers("/process_register").permitAll()
//                .antMatchers("/signup").permitAll()
//                .antMatchers("/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
////                    .httpBasic();
//                .formLogin(form -> form.loginPage("/login")
//                        .defaultSuccessUrl("/home", true)
//                        .permitAll())
//                .logout(logout -> {
//                    logout.logoutUrl("/logout").logoutSuccessUrl("/home");
//                });
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////                      Criar usuario inicial.
////        UserDetails user =
////                User.builder()
////                        .username("hugo")
////                        .password(bCryptPasswordEncoder.encode("hugo"))
////                        .roles("ADM")
////                        .build();
//
//        auth
//
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(bCryptPasswordEncoder);
////                .withUser(user);
//
//
//    }


}
