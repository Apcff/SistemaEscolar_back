package com.api.SistemaEscolar_back.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.api.SistemaEscolar_back.domain.Turma;
import com.api.SistemaEscolar_back.domain.dtos.TurmaDTO;
import com.api.SistemaEscolar_back.services.TurmaService;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/service/turmas")
public class TurmaResource {

    @Autowired
    private TurmaService turmaService;

    @GetMapping(value="/{id}")
    public ResponseEntity<TurmaDTO> findById(@PathVariable Integer id){
        Turma turma = this.turmaService.findById(id);
        return ResponseEntity.ok().body(new TurmaDTO(turma));
    }

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> findAll() {
        List<Turma> turmas = turmaService.findAll();
        List<TurmaDTO> turmasDTO = turmas.stream().map(obj -> new TurmaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(turmasDTO);
    }

    @PostMapping
    public ResponseEntity<TurmaDTO> create(@Valid @RequestBody TurmaDTO turmaDTO){
        Turma turma = turmaService.create(turmaDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

	@DeleteMapping(value="{id}")
	public ResponseEntity<TurmaDTO> delete(@PathVariable Integer id){
		turmaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
