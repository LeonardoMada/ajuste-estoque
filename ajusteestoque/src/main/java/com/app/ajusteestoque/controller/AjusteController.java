package com.app.ajusteestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ajusteestoque.entity.Ajuste;
import com.app.ajusteestoque.service.AjusteService;

@RestController
@Secured("admin") // regra de usuário permitido
@RequestMapping("/ajuste")
public class AjusteController {

    @Autowired
    private AjusteService ajusteService;

    @PostMapping("/adicionar")
    public ResponseEntity<Object> adicionarAjuste(@RequestBody Ajuste ajuste) {
        return ResponseEntity.ok(ajusteService.insertAjuste(ajuste));
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<Object> excluirAjuste(@RequestParam int id) {
        return ResponseEntity.ok(ajusteService.deleteAjuste(id));
    }

    @GetMapping("/obter")
    public ResponseEntity<Object> obterAjuste(@RequestParam int id) {
        return ResponseEntity.ok(ajusteService.selectAjuste(id));
    }

    @GetMapping("/obterTodos")
    public ResponseEntity<Object> obterTodos() {
        return ResponseEntity.ok(ajusteService.selectAjuste());
    }
}
