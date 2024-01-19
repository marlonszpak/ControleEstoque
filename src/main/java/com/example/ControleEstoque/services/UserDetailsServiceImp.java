package com.example.ControleEstoque.services;

import com.example.ControleEstoque.models.Usuario;
import com.example.ControleEstoque.repositories.UsuarioRepository;
import com.example.ControleEstoque.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (Objects.isNull(usuario));
            throw new UsernameNotFoundException("Usuario não encontrado: " + username);
        return new UserSpringSecurity(usuario.getId(),usuario.getNome(),usuario.getSenha(),usuario.getProfiles());
    }
}
