package com.karven.config;

import com.karven.service.security.KarvenFilterSecurityInterceptor;
import com.karven.service.security.KarvenUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity  //启动SpringSecurity过滤器链
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private KarvenUserDetailsService karvenUserDetailsService;

    @Autowired
    private KarvenFilterSecurityInterceptor karvenFilterSecurityInterceptor;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 该方法的作用是代替<security:authentication-manager>
     * 配置认证信息的提供
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(karvenUserDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * 该方法的作用是代替：<security:http>
     * 配置拦截资源 过滤器 登录方式等
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()   //任何请求,登录后可以访问
                .and()
                .formLogin().loginPage("/login")    //登录页面用户任意访问
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout().permitAll()   //注销行为任意访问
                .and().csrf().disable();
        http.addFilterBefore(karvenFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }
}
