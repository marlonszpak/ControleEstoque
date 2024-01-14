package com.example.ControleEstoque.dtos;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRecordDTO(@NotBlank String nome, @NotBlank String email, @NotBlank String senha) {
}
