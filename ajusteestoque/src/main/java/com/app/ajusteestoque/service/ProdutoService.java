package com.app.ajusteestoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ajusteestoque.entity.Produto;
import com.app.ajusteestoque.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto insertProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public boolean deleteProduto(int id) {
        try {
            produtoRepository.deleteById((long) id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Produto selectProduto(int id) {
        return produtoRepository.findById((long) id).orElse(null);
    }

    public List<Produto> selectProduto() {
        return produtoRepository.findAll();
    }
}
