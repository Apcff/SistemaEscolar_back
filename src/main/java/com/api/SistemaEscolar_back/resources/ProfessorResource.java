package com.api.SistemaEscolar_back.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.api.SistemaEscolar_back.domain.Professor;
import com.api.SistemaEscolar_back.domain.dtos.ProfessorDTO;
import com.api.SistemaEscolar_back.services.ProfessorService;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/service/professores")
public class ProfessorResource {

    @Autowired
    private ProfessorService professorService;

    @GetMapping(value="/{id}")
    public ResponseEntity<ProfessorDTO> findById(@PathVariable Integer id){
        Professor professor = this.professorService.findById(id);
        return ResponseEntity.ok().body(new ProfessorDTO(professor));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        List<Professor> professores = professorService.findAll();
        List<ProfessorDTO> professoresDTO = professores.stream().map(obj -> new ProfessorDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(professoresDTO);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> create(@Valid @RequestBody ProfessorDTO professorDTO){
        Professor newProfessor = professorService.create(professorDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProfessor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable Integer id, @Valid @RequestBody ProfessorDTO professorDTO){
        Professor newProfessor = professorService.update(id, professorDTO);
        return ResponseEntity.ok().body(new ProfessorDTO(newProfessor));
    }
    
	@DeleteMapping(value="{id}")
	public ResponseEntity<ProfessorDTO> delete(@PathVariable Integer id){
		professorService.delete(id);
		return ResponseEntity.noContent().build();
	}

}