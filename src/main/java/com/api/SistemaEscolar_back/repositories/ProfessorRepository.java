package com.api.SistemaEscolar_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.SistemaEscolar_back.domain.Professor;



public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
