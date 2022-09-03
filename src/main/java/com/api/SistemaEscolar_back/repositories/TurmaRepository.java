package com.api.SistemaEscolar_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.SistemaEscolar_back.domain.Turma;



public interface TurmaRepository extends JpaRepository<Turma, Integer> {
}
