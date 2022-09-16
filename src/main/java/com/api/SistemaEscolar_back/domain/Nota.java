package com.api.SistemaEscolar_back.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.api.SistemaEscolar_back.domain.dtos.NotaDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Nota implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    protected String nota;
    
    protected Integer aluno_id;
    
    public Nota() {
        super();
    }
    
       
	public Nota(NotaDTO notaDTO) {
		this.id = notaDTO.getId();
		this.nota = notaDTO.getNota();
		this.aluno_id = notaDTO.getAluno_id();
	}
	

	public Nota(Integer id, String nota, Integer aluno_id) {
		super();
		this.id = id;
		this.nota = nota;
		this.aluno_id = aluno_id;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Integer getAluno_id() {
		return aluno_id;
	}

	public void setAluno_id(Integer aluno_id) {
		this.aluno_id = aluno_id;
	}

}
