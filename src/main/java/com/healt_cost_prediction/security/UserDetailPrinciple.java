package com.healt_cost_prediction.security;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.healt_cost_prediction.modal.Operator;
public class UserDetailPrinciple implements UserDetails {
    private Operator operator;

    public UserDetailPrinciple(Operator data) {
        this.operator = data;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(String.valueOf(operator.getRole()));
        return Collections.singleton(auth);
    }

    @Override
    public String getPassword() {
        return operator.getPassord();
    }

    @Override
    public String getUsername() {
        return operator.getEmail();
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
        return true;
    }

}