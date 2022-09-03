package com.api.SistemaEscolar_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.SistemaEscolar_back.domain.Nota;
import com.api.SistemaEscolar_back.domain.dtos.NotaDTO;
import com.api.SistemaEscolar_back.repositories.NotaRepository;
import com.api.SistemaEscolar_back.services.exceptions.DataIntegrityViolationException;
import com.api.SistemaEscolar_back.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {


    @Autowired
    private NotaRepository notaRepository;

    public Nota findById(Integer id){
        Optional<Nota> nota= notaRepository.findById(id);
        return nota.orElseThrow(() -> new ObjectNotFoundException("Nota não encontrada!: "+id));
    }

    public List<Nota> findAll() {
        return notaRepository.findAll();
    }

    public Nota create(NotaDTO notaDTO) {
        notaDTO.setId(null);
        Nota newNota = new Nota(notaDTO);
        return notaRepository.save(newNota);
    }

    public Nota update(Integer id, NotaDTO notaDto) {
        notaDto.setId(id);
        Nota oldNota = findById(id);
        oldNota = new Nota(notaDto);
        return notaRepository.save(oldNota);
    }

    public void delete(Integer id) {
		Nota obj = findById(id);
		if(obj.getId() == 0) {
			throw new DataIntegrityViolationException("A Nota: "+ id +" não tem no sistema: "+	obj.getId());
		}
		notaRepository.deleteById(id);
	}
}
