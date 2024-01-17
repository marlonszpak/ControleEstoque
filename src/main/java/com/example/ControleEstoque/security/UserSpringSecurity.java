package com.example.ControleEstoque.security;

import com.example.ControleEstoque.models.enums.ProfileEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class UserSpringSecurity implements UserDetails {

    private Long id;
    private String nome;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(Long id, String nome, String senha, Set<ProfileEnum> profileEnums) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.authorities = profileEnums.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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

    public boolean hasRole(ProfileEnum profileEnum){
        return getAuthorities().contains(new SimpleGrantedAuthority(profileEnum.getDescription()));
    }

}
