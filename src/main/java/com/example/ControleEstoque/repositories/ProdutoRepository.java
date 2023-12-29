package com.example.ControleEstoque.repositories;

import com.example.ControleEstoque.models.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}