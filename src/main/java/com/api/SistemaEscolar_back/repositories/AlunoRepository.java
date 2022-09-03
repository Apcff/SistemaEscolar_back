package com.api.SistemaEscolar_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.SistemaEscolar_back.domain.Aluno;



public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
