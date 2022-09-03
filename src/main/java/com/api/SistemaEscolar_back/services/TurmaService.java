package com.api.SistemaEscolar_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.SistemaEscolar_back.domain.Professor;
import com.api.SistemaEscolar_back.domain.Turma;
import com.api.SistemaEscolar_back.domain.dtos.TurmaDTO;
import com.api.SistemaEscolar_back.repositories.TurmaRepository;
import com.api.SistemaEscolar_back.services.exceptions.DataIntegrityViolationException;
import com.api.SistemaEscolar_back.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository repository;

    
    public Turma findById(Integer id){
        Optional<Turma> turma = repository.findById(id);
        return turma.orElseThrow(() -> new ObjectNotFoundException("Turma não encontrada!: "+id));
    }
    public List<Turma> findAll() {
        return repository.findAll();
    }

    public Turma create(TurmaDTO turmaDTO) {
        turmaDTO.setId(null);
        return repository.save(newTurma(turmaDTO));
    }

    private Turma newTurma(TurmaDTO turmaDTO){
        Turma turma = new Turma();

        Professor professor = new Professor();
        professor.setId(turmaDTO.getProfessorId());

        turma.setDescricao(turmaDTO.getDescricao());
        turma.setProfessor(professor);

        return turma;
    }

    public Turma update(Integer id, TurmaDTO turmaDTO) {
        turmaDTO.setId(id);
        Turma oldTurma = findById(id);
        oldTurma = new Turma(turmaDTO);
        return repository.save(oldTurma);
    }

    public void delete(Integer id) {
		Turma obj = findById(id);
		if(obj.getId() == 0) {
			throw new DataIntegrityViolationException("A Turma: "+	id +" não existe no sistema: "+ obj.getId());
		}
		repository.deleteById(id);
	}
}
