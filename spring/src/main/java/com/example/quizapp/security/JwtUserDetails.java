package com.example.quizapp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.quizapp.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    public Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> getAuthorities;

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    // }

    // constrcuter
    private JwtUserDetails(Long id, String username, String password,
            Collection<? extends GrantedAuthority> getAuthorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.getAuthorities = getAuthorities;
    }

    public static JwtUserDetails create(User user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(), authoritiesList);
    }

    // public static JwtUserDetails create(Long id, String username, String
    // password, Collection<? extends GrantedAuthority> getAuthorities) {
    // return new JwtUserDetails(id, username, password, getAuthorities);
    // }

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
