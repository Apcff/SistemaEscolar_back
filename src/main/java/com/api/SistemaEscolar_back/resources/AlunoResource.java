package com.api.SistemaEscolar_back.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.api.SistemaEscolar_back.domain.Aluno;
import com.api.SistemaEscolar_back.domain.dtos.AlunoDTO;
import com.api.SistemaEscolar_back.services.AlunoService;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/service/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @GetMapping(value="/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Integer id){
        Aluno aluno = this.alunoService.findById(id);
        return ResponseEntity.ok().body(new AlunoDTO(aluno));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<Aluno> alunos = alunoService.findAll();
        List<AlunoDTO> alunosDTO = alunos.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(alunosDTO);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> create(@Valid @RequestBody AlunoDTO alunoDTO){
        Aluno newAluno = alunoService.create(alunoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAluno.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value="/{id}")
	  public ResponseEntity<AlunoDTO> update(@PathVariable Integer id, @Valid @RequestBody AlunoDTO alunoDTO){
		 Aluno newAluno = alunoService.update(id, alunoDTO);
	        return ResponseEntity.ok().body(new AlunoDTO(newAluno));
	    }
    
	@DeleteMapping(value="{id}")
	public ResponseEntity<AlunoDTO> delete(@PathVariable Integer id){
		alunoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
