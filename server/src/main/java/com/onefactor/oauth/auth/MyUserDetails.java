package com.onefactor.oauth.auth;

import com.onefactor.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created on 07.03.18.
 */
@AllArgsConstructor
public class MyUserDetails implements UserDetails, Serializable {

    private Collection<? extends GrantedAuthority> authorities;
    private String username;
    private String password;
    private boolean isEnabled;

    public MyUserDetails(User user) {
        this.username = user.getName();
        this.password = user.getPassword();
        this.isEnabled = user.isEnabled();
        this.authorities = user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return  username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
