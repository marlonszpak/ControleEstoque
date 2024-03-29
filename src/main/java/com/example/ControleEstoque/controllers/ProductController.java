package com.example.ControleEstoque.controllers;

import com.example.ControleEstoque.models.Product;
import com.example.ControleEstoque.repositories.ProductRepository;
import com.example.ControleEstoque.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/estoque")
@Validated
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<Iterable<Product>> findById(@PathVariable Long id){
        Product obj = this.productService.findById(id);
        return ResponseEntity.ok(Collections.singleton(obj));
    }
    @GetMapping("/product")
    public ResponseEntity getAllProducts(){
        List<Product> productList = this.productRepository.findAll();
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/product")
    @Validated
    public ResponseEntity<Void> create(@Valid Product obj){
        this.productService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/product/{id}")
    @Validated
    private ResponseEntity<Void> update(@Valid @RequestBody Product obj, @PathVariable Long id){
        obj.setId(id);
        this.productService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/product/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
