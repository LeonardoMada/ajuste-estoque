package com.app.ajusteestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ajusteestoque.entity.Produto;
import com.app.ajusteestoque.service.ProdutoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionarProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.insertProduto(produto));
    }

    @PutMapping("/alterar")
    public ResponseEntity<Object> alterarProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.updateProduto(produto));
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<Object> excluirProduto(@RequestParam int id) {
        return ResponseEntity.ok(produtoService.deleteProduto(id));
    }

    @GetMapping("/obter")
    public ResponseEntity<Object> obterProduto(@RequestParam int id) {
        return ResponseEntity.ok(produtoService.selectProduto(id));
    }

    @GetMapping("/obterTodos")
    public ResponseEntity<Object> obterTodos() {
        return ResponseEntity.ok(produtoService.selectProduto());
    }
}
