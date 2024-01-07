package com.example.ControleEstoque.controllers;

import com.example.ControleEstoque.dtos.ProdutoRecordDTO;
import com.example.ControleEstoque.models.Produto;
import com.example.ControleEstoque.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/estoque")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/produto")
    public ResponseEntity<Produto> novoProduto(@Valid ProdutoRecordDTO produtoRecordDTO){
        var produto = new Produto();
        BeanUtils.copyProperties(produtoRecordDTO, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> obterTodosProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body((List<Produto>) produtoRepository.findAll());
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Iterable> obterProdutoPorId(@PathVariable(value = "id") Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singleton("Produto não encontrado."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singleton(produto.get()));
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Object> alterarProduto(@PathVariable(value = "id") Long id, @Valid ProdutoRecordDTO produtoRecordDTO){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        var prod = produto.get();
        BeanUtils.copyProperties(produtoRecordDTO, prod);
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(prod));
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable(value = "id") Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        produtoRepository.delete(produto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produuto deletado");
    }
}