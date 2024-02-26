package com.example.ControleEstoque.services;

import com.example.ControleEstoque.models.Product;
import com.example.ControleEstoque.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findById(Long id){
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException(
                "Produto não encontrado! id: " + id + "Tipo: " + Product.class.getName()));
    }

    @Transactional
    public Product create(Product obj){
        obj.setId(null);
        obj = this.productRepository.save(obj);
        return obj;
    }

    @Transactional
    public Product update(Product obj){
        Product newProduct = findById(obj.getId());
        newProduct.setName(obj.getName());
        newProduct.setName(obj.getReference());
        newProduct.setName(String.valueOf(obj.getAmount()));
        return this.productRepository.save(newProduct);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.productRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas!");
        }
    }
}
