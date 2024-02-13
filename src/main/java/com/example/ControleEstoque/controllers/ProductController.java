package com.example.ControleEstoque.controllers;

import com.example.ControleEstoque.models.Product;
import com.example.ControleEstoque.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/product")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = this.productService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid Product obj){
        this.productService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    private ResponseEntity<Void> update(@Valid @RequestBody Product obj, @PathVariable Long id){
        obj.setId(id);
        this.productService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    private ResponseEntity<Void> delete(@PathVariable Long id){
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
