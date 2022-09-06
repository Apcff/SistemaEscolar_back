package com.api.SistemaEscolar_back.domain;

import javax.persistence.*;

import com.api.SistemaEscolar_back.domain.dtos.ProfessorDTO;
import com.api.SistemaEscolar_back.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Professor extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
   
    // Uma turma pode ter um professor e um professor pode ter várias turmas
    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private List<Turma> turmas = new ArrayList<>();

    private String formacao;

    public Professor(){
        super();
        addPerfis(Perfil.PROFESSOR);
    };

    public Professor(ProfessorDTO professorDTO) {
        super();
        this.id = professorDTO.getId();
        this.nome = professorDTO.getNome();
        this.cpf = professorDTO.getCpf();
        this.email = professorDTO.getEmail();
        this.senha = professorDTO.getSenha();
        this.perfis = professorDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = professorDTO.getDataCriacao();
        this.formacao = professorDTO.getFormacao();
   
        addPerfis(Perfil.PROFESSOR);
    }

    public Professor(Integer id, String nome, String cpf, String email, String senha, String formacao) {
        super(id, nome, cpf, email, senha);
        this.formacao = formacao;
        addPerfis(Perfil.PROFESSOR);
    }

    public List<Turma> getTurmas() {
        return turmas;
    }
    

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
}
