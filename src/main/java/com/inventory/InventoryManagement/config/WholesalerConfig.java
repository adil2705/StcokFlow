package com.inventory.InventoryManagement.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.inventory.InventoryManagement.entity.Wholesaler;

public class WholesalerConfig implements UserDetails {
        
    private final Wholesaler wholesaler;

    public WholesalerConfig(Wholesaler wholesaler) {
        this.wholesaler = wholesaler;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+wholesaler.getRole().name()));  
    }

    @Override
    public String getPassword() {
        return wholesaler.getPassword(); 
    }

    @Override
    public String getUsername() {
        return wholesaler.getEmail();  
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
