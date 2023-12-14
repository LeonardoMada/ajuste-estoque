package com.app.ajusteestoque.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Service;
import com.app.ajusteestoque.entity.Usuario;

@Service
public class TokenService {

    private String chaveSecreta = "CHAVE_SECRETA";

    public String gerarToken(Usuario usuario) {

        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);
        Date expirationDate = Date.from(expiresAt.toInstant(ZoneOffset.of("-03:00")));

        return JWT.create()
                .withIssuer("Produtos")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(expirationDate)
                // LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"))
                .sign(Algorithm.HMAC256(chaveSecreta));

    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(chaveSecreta))
                .withIssuer("Produtos")
                .build().verify(token).getSubject();
    }

}
