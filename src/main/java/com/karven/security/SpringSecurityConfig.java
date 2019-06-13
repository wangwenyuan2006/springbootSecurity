package com.karven.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity  //启动SpringSecurity过滤器链
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 该方法的作用是代替<security:authentication-manager>
     * 配置认证信息的提供
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("karven")
//                .password("123456")
//                .authorities("ROLE_ADD_PRODUCT", "ROLE_UPDATE_PRODUCT")
//                .and()
//                .withUser("sandal")
//                .password("123456")
//                .authorities("ROLE_DELETE_PRODUCT", "ROLE_LIST_PRODUCT");
        auth.userDetailsService(new KarvenUserDetailsService());
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
        //拦截所有请求
        http.authorizeRequests()
                .antMatchers("/product/add").hasAuthority("ROLE_ADD_PRODUCT")
                .antMatchers("/product/update").hasAuthority("ROLE_UPDATE_PRODUCT")
                .antMatchers("/product/delete").hasAuthority("ROLE_DELETE_PRODUCT")
                .antMatchers("/product/list").hasAuthority("ROLE_LIST_PRODUCT")
                .antMatchers("/login").permitAll()
                .antMatchers("/**").fullyAuthenticated()
                .and().formLogin().loginPage("/login")
                .and().csrf().disable();
    }
}
