package com.ymatou.alarmcenter.admin.web.security;


import com.ymatou.alarmcenter.domain.model.Operator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.*;

/**
 * @author Siva
 */
public class AuthenticatedUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;
    private Operator user;

    public AuthenticatedUser(Operator user) {
        super(user.getLoginId(), user.getPassword(), getAuthorities(user));
        this.user = user;
    }

    public Operator getUser() {
        return user;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Operator user) {
        Set<String> roleAndPermissions = new HashSet<>();
        List<String> roles = user.getRoles();
        if (roles == null)
            roles = new ArrayList<>();
        for (String role : roles) {
            roleAndPermissions.add(role);
        }
        String[] roleNames = new String[roleAndPermissions.size()];
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
        return authorities;
    }
}
