package com.api.SistemaEscolar_back.domain.dtos;


import com.api.SistemaEscolar_back.domain.Professor;
import com.api.SistemaEscolar_back.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfessorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    @NotNull(message = "O Campo NOME é requerido!")
    protected String nome;

    @NotNull(message = "O campo CPF é requerido!")
    protected String cpf;

    @NotNull(message = "O campo E-MAIL é requerido!")
    protected String email;

    @NotNull(message = "O campo SENHA é requerido!")
    protected String senha;

    @NotNull(message = "O campo FORMACAO é requerido!")
    protected String formacao;

    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public ProfessorDTO() {
        super();
        addPerfis(Perfil.PROFESSOR);
    }

    public ProfessorDTO(Professor professor) {
        super();
        this.id = professor.getId();
        this.nome = professor.getNome();
        this.cpf = professor.getCpf();
        this.email = professor.getEmail();
        this.senha = professor.getSenha();
        this.perfis = professor.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = professor.getDataCriacao();
        this.formacao = professor.getFormacao();
        addPerfis(Perfil.PROFESSOR);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x-> Perfil.toEnum(x)).collect(Collectors.toSet());
    }
    public void addPerfis(Perfil perfis) {
        this.perfis.add(perfis.getCodigo());
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
}
