package com.ymatou.alarmcenter.admin.web.security;

import com.ymatou.alarmcenter.domain.model.Operator;
import com.ymatou.alarmcenter.domain.repository.OperatorRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhangxiaoming on 2016/12/14.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private OperatorRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        Operator user = userRepository.getOperatorByLoginId(userName);
        if (user == null) {
            throw new UsernameNotFoundException("LoginId " + userName + " not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getLoginId(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Operator user) {
        Set<String> userRoles = new HashSet<>();
        List<String> roles = user.getRoles();
        if (roles == null)
            roles = new ArrayList<>();
        for (String role : roles) {
            userRoles.add(role);
        }
        String[] roleNames = new String[userRoles.size()];
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles.toArray(roleNames));
        return authorities;
    }
}

