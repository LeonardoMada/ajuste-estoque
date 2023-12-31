package com.app.ajusteestoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.ajusteestoque.entity.Ajuste;
import com.app.ajusteestoque.entity.Produto;
import com.app.ajusteestoque.enumeration.Tipo;
import com.app.ajusteestoque.repository.AjusteRepository;
import com.app.ajusteestoque.repository.ProdutoRepository;

@Service
public class AjusteService {

    @Autowired
    private AjusteRepository ajusteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Ajuste insertAjuste(Ajuste ajuste) {

        Produto produto = produtoRepository.findById(ajuste.getProduto().getId()).orElse(null);

        if (produto != null) {
            if (ajuste.getTipo() == Tipo.Retirada) {

                if (produto.getQuantidade() >= ajuste.getQuantidade()) {
                    produto.setQuantidade(
                            produto.getQuantidade() - ajuste.getQuantidade());
                } else {
                    return null; // Tratar uma mensagem de erro
                }

            } else if (ajuste.getTipo() == Tipo.Adição) {

                produto.setQuantidade(
                        produto.getQuantidade() + ajuste.getQuantidade());
            } else {
                return null; // Tratar uma mensagem de erro
            }
            produtoRepository.save(produto);
            return ajusteRepository.save(ajuste);
        }
        return null; // Tratar uma mensagem de erro
    }

    @Transactional
    public boolean deleteAjuste(int id) {
        try {
            Ajuste ajuste = ajusteRepository.findById((long) id).orElse(null);
            Produto produto = ajuste.getProduto();

            if (ajuste.getTipo() == Tipo.Retirada) {
                produto.setQuantidade(
                        produto.getQuantidade() + ajuste.getQuantidade());
            } else if (ajuste.getTipo() == Tipo.Adição) {
                produto.setQuantidade(
                        produto.getQuantidade() - ajuste.getQuantidade());
            } else {
                return false; // Tratar uma mensagem de erro
            }

            produtoRepository.save(produto);
            ajusteRepository.deleteById((long) id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Ajuste selectAjuste(int id) {
        return ajusteRepository.findById((long) id).orElse(null);
    }

    public List<Ajuste> selectAjuste() {
        return ajusteRepository.findAll();
    }
}
