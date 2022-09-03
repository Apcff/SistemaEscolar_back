package com.api.SistemaEscolar_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.SistemaEscolar_back.domain.Aluno;
import com.api.SistemaEscolar_back.domain.Pessoa;
import com.api.SistemaEscolar_back.domain.dtos.AlunoDTO;
import com.api.SistemaEscolar_back.repositories.AlunoRepository;
import com.api.SistemaEscolar_back.repositories.PessoaRepository;
import com.api.SistemaEscolar_back.services.exceptions.DataIntegrityViolationException;
import com.api.SistemaEscolar_back.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Aluno findById(Integer id){
        Optional<Aluno> aluno= repository.findById(id);
        return aluno.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado!: "+id));
    }

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Aluno create(AlunoDTO alunoDTO) {
        alunoDTO.setId(null);
        validaPorCpfEEmail(alunoDTO);
        Aluno newAluno = new Aluno(alunoDTO);
        return repository.save(newAluno);
    }

    private void validaPorCpfEEmail(AlunoDTO alunoDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(alunoDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != alunoDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(alunoDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != alunoDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já existente no sistema!");
        }
    }

    public Aluno update(Integer id, AlunoDTO alunoDto) {
        alunoDto.setId(id);
        Aluno oldAluno = findById(id);
        validaPorCpfEEmail(alunoDto);
        oldAluno = new Aluno(alunoDto);
        return repository.save(oldAluno);
    }
    
    public void delete(Integer id) {
		Aluno obj = findById(id);
		if(obj.getId() == 0) {
			throw new DataIntegrityViolationException("O Aluno: "+	id + " não tem no sistema: " +	obj.getId());
		}
		repository.deleteById(id);
	}
}
