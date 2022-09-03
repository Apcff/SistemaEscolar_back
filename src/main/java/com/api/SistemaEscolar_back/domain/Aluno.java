package com.api.SistemaEscolar_back.domain;

import javax.persistence.*;

import com.api.SistemaEscolar_back.domain.dtos.AlunoDTO;
import com.api.SistemaEscolar_back.domain.enums.Perfil;
import java.io.Serializable;
import java.util.stream.Collectors;

@Entity
public class Aluno extends Pessoa implements Serializable{
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @JoinColumn(name="turmaid")
    protected Integer turmaID;

    public Aluno() {
        super();
        addPerfis(Perfil.ALUNO);
    }

    public Aluno(AlunoDTO alunoDTO) {
        super();
        this.id = alunoDTO.getId();
        this.nome = alunoDTO.getNome();
        this.cpf = alunoDTO.getCpf();
        this.email = alunoDTO.getEmail();
        this.senha = alunoDTO.getSenha();
        this.perfis = alunoDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = alunoDTO.getDataCriacao();
        this.turmaID = alunoDTO.getTurmaID();
        addPerfis(Perfil.ALUNO);
    }

    public Aluno(Integer id, String nome, String cpf, String email, String senha, Turma turma, String nota) {
        super(id, nome, cpf, email, senha);
        this.turmaID = turma.getId();
        addPerfis(Perfil.ALUNO);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTurmaID() {
        return turmaID;
    }

    public void setTurmaID(Integer turmaID) {
        this.turmaID = turmaID;
    }
}
