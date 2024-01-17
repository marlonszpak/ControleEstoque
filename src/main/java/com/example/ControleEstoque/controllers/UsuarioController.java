package com.example.ControleEstoque.controllers;

import com.example.ControleEstoque.dtos.UsuarioRecordDTO;
import com.example.ControleEstoque.models.Usuario;
import com.example.ControleEstoque.models.enums.ProfileEnum;
import com.example.ControleEstoque.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping
@CrossOrigin
public class UsuarioController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> novoUsuario(@Valid UsuarioRecordDTO usuarioRecordDTO){
        var usuario = new Usuario();
        usuario.setSenha(this.bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuario.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        BeanUtils.copyProperties(usuarioRecordDTO, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> obterTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body((List<Usuario>) usuarioRepository.findAll());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Iterable> obterUsuarioPorId(@PathVariable(value = "id") Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singleton("Usuario não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singleton(usuario.get()));
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Object> alterarUsuario(@PathVariable(value = "id") Long id, @Valid UsuarioRecordDTO usuarioRecordDTO){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        var user = usuario.get();
        user.setSenha(this.bCryptPasswordEncoder.encode(user.getSenha()));
        BeanUtils.copyProperties(usuarioRecordDTO, user);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(user));
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "id") Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        usuarioRepository.delete(usuario.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado");
    }
}