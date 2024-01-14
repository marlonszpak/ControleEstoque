package com.example.ControleEstoque.dtos;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRecordDTO(@NotBlank nome,@NotBlank email,@NotBlank senha) {
}
