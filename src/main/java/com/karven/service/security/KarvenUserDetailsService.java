package com.karven.service.security;

import com.karven.entity.user.Permission;
import com.karven.entity.user.User;
import com.karven.mapper.security.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class KarvenUserDetailsService implements UserDetailsService {
    private Logger logger = Logger.getLogger(KarvenUserDetailsService.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);

        if (null == user) {
            return user;
        }

        List<Permission> permissions = userMapper.findPermissionByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        permissions.forEach(permission -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(permission.getPermTag());
            authorities.add(authority);
        });

        user.setAuthorities(authorities);
        //UserDetails: 封装用户数据的接口
        //spring security提供了默认的UserDetails实现类，也可以自己实现
//        User user = new User("karven2","123456",AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));
        logger.info(user);

        return user;
    }
}
