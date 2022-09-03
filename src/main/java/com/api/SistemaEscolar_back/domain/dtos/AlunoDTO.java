package com.api.SistemaEscolar_back.domain.dtos;


import com.api.SistemaEscolar_back.domain.Aluno;
import com.api.SistemaEscolar_back.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AlunoDTO implements Serializable {
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

    @NotNull(message = "O campo TURMAID é requerido!")
    protected Integer turmaID;

    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();
    
   
    public AlunoDTO() {
        super();
        addPerfis(Perfil.ALUNO);
    }

    public AlunoDTO(Aluno aluno) {
        super();
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.cpf = aluno.getCpf();
        this.email = aluno.getEmail();
        this.senha = aluno.getSenha();
        this.perfis = aluno.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = aluno.getDataCriacao();
        this.turmaID = aluno.getTurmaID();
        addPerfis(Perfil.ALUNO);
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

    public Integer getTurmaID() {
        return turmaID;
    }

    public void setTurmaID(Integer turmaID) {
        this.turmaID = turmaID;
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
}
