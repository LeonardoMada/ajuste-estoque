package com.app.ajusteestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ajusteestoque.entity.Ajuste;

@Repository
public interface AjusteRepository extends JpaRepository<Ajuste, Long> {

}
