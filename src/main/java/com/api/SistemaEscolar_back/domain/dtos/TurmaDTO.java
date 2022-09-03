package com.api.SistemaEscolar_back.domain.dtos;

import javax.validation.constraints.NotNull;

import com.api.SistemaEscolar_back.domain.Turma;

import java.io.Serializable;

public class TurmaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "O campo DESCRIÇÃO é requerido.")
    private String descricao;

    @NotNull(message = "O campo PROFESSORID é requerido.")
    private Integer professorId;

    public TurmaDTO() {
        super();
    }

    public TurmaDTO(Turma turma) {
        super();
        this.id = turma.getId();
        this.descricao = turma.getDescricao();
        this.professorId = turma.getProfessor().getId();
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

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }


}
