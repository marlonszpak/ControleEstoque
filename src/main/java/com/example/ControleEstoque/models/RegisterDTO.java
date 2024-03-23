package com.example.ControleEstoque.models;

import com.example.ControleEstoque.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
