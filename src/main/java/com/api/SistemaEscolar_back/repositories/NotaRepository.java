package com.api.SistemaEscolar_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.SistemaEscolar_back.domain.Nota;



public interface NotaRepository extends JpaRepository<Nota, Integer> {
}
