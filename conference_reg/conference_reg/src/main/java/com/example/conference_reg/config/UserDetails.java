package com.example.conference_reg.config;

import com.example.conference_reg.entity.Owner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private  String username;
    private String password;
    private List<GrantedAuthority> authorities;
    public UserDetails(Owner owner){
        username = owner.getUsername();
        password = owner.getPassword();
        authorities = Arrays.stream(owner.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//      return Collections.singleton(new SimpleGrantedAuthority("Owner"));
        //  System.out.println("12334"+ authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
