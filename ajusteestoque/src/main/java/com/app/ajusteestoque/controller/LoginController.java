package com.app.ajusteestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ajusteestoque.dto.LoginDto;
import com.app.ajusteestoque.entity.Usuario;
import com.app.ajusteestoque.service.TokenService;

@RestController
@RequestMapping("/security")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto login) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    login.login(),
                    login.password());

            Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            Usuario usuario = (Usuario) authenticate.getPrincipal();

            return ResponseEntity.ok().body(tokenService.gerarToken(usuario));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }
    }
}
