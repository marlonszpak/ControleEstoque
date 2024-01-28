package com.example.ControleEstoque.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = Product.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    public static final String TABLE_NAME = "product";

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    @Size(min = 2, max = 100)
    @NotBlank
    private String name;

    @Column(name = "reference", length = 60, nullable = false)
    @Size(min = 8, max = 60)
    @NotBlank
    private String reference;

    @Column(name = "amount")
    @NotBlank
    private Integer amount;

    @Column(name = "profile", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_profile")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> profiles = new HashSet<>();

//    public Set<ProfileEnum> getProfiles() {
//        return this.profiles.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());
//    }
//
//    public void addProfile(ProfileEnum profileEnum) {
//        this.profiles.add(profileEnum.getCode());
//    }

}