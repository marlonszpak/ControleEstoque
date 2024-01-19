package com.example.ControleEstoque.repositories;

import com.example.ControleEstoque.models.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    @Transactional
    Usuario findByUsername(String username);
}
