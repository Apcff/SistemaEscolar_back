package com.api.SistemaEscolar_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.SistemaEscolar_back.domain.Pessoa;
import com.api.SistemaEscolar_back.domain.Professor;
import com.api.SistemaEscolar_back.domain.dtos.ProfessorDTO;
import com.api.SistemaEscolar_back.repositories.PessoaRepository;
import com.api.SistemaEscolar_back.repositories.ProfessorRepository;
import com.api.SistemaEscolar_back.services.exceptions.DataIntegrityViolationException;
import com.api.SistemaEscolar_back.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Professor findById(Integer id){
        Optional<Professor> professor= repository.findById(id);
        return professor.orElseThrow(() -> new ObjectNotFoundException("Professor não encontrado!: "+id));
    }

    public List<Professor> findAll() {
        return repository.findAll();
    }

    public Professor create(ProfessorDTO professorDTO) {
        professorDTO.setId(null);
        validaPorCpfEEmail(professorDTO);
        Professor newProfessor = new Professor(professorDTO);
        return repository.save(newProfessor);
    }

    private void validaPorCpfEEmail(ProfessorDTO professorDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(professorDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != professorDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(professorDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != professorDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já existente no sistema!");
        }
    }

    public Professor update(Integer id, ProfessorDTO professorDTO) {
        professorDTO.setId(id);
        Professor oldProfessor = findById(id);
        validaPorCpfEEmail(professorDTO);
        oldProfessor = new Professor(professorDTO);
        return repository.save(oldProfessor);
    }
    
    public void delete(Integer id) {
		Professor obj = findById(id);
		if(obj.getId() == 0) {
			throw new DataIntegrityViolationException("O Professor: "+ id + " não tem no sistema: "+ obj.getId());
		}
		repository.deleteById(id);
	}


}
