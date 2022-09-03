package com.api.SistemaEscolar_back.domain;
import javax.persistence.*;

import com.api.SistemaEscolar_back.domain.dtos.TurmaDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "turmaID")
    private List<Aluno> alunos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="professor_id")
    private Professor professor;

    private String descricao;

    public Turma() {
        super();
    }

    public Turma(TurmaDTO turmaDTO) {
        super();
        this.id = turmaDTO.getId();
        this.descricao = turmaDTO.getDescricao();
        this.professor.id = turmaDTO.getProfessorId();
    }

    public Turma(Integer id, String descricao, Professor  professor) {
        super();
        this.id = id;
        this.descricao = descricao;
        this.professor = professor;
        this.professor.id =  professor.id;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
