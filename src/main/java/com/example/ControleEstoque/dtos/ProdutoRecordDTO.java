package com.example.ControleEstoque.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRecordDTO(@NotBlank String nome, @NotBlank String endereco, @NotNull Integer quantidade) {
}