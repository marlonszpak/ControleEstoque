package com.example.ControleEstoque.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.ControleEstoque.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = User.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {

    public static final String TABLE_NAME = "user";

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @Size(min = 2, max = 100)
    @NotBlank
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @Size(min = 8, max = 60)
    @NotBlank
    private String password;

    private UserRole role;


    //    @Column(name = "profile", nullable = false)
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_profile")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Set<Integer> profiles = new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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

//    public Set<ProfileEnum> getProfiles() {
//        return this.profiles.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());
//    }
//
//    public void addProfile(ProfileEnum profileEnum) {
//        this.profiles.add(profileEnum.getCode());
//    }

}