package com.api.SistemaEscolar_back.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.api.SistemaEscolar_back.domain.Nota;
import com.api.SistemaEscolar_back.domain.dtos.NotaDTO;
import com.api.SistemaEscolar_back.services.NotaService;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "**")
@RestController
@RequestMapping(value="/service/notas")
public class NotaResource {

    @Autowired
    private NotaService notaService;

    @GetMapping(value="/{id}")
    public ResponseEntity<NotaDTO> findById(@PathVariable Integer id){
        Nota nota = this.notaService.findById(id);
        return ResponseEntity.ok().body(new NotaDTO(nota));
    }

    @GetMapping
    public ResponseEntity<List<NotaDTO>> findAll() {
        List<Nota> notas = notaService.findAll();
        List<NotaDTO> notasDTO = notas.stream().map(obj -> new NotaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(notasDTO);
    }

    @PostMapping
    public ResponseEntity<NotaDTO> create(@Valid @RequestBody NotaDTO notaDTO){
        Nota newNota = notaService.create(notaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newNota.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value="/{id}")
	  public ResponseEntity<NotaDTO> update(@PathVariable Integer id, @Valid @RequestBody NotaDTO notaDTO){
		Nota newNota = notaService.update(id, notaDTO);
	        return ResponseEntity.ok().body(new NotaDTO(newNota));
	    }
    
	@DeleteMapping(value="{id}")
	public ResponseEntity<NotaDTO> delete(@PathVariable Integer id){
		notaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
