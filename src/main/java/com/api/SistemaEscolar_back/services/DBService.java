package com.api.SistemaEscolar_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.SistemaEscolar_back.domain.Aluno;
import com.api.SistemaEscolar_back.domain.Nota;
import com.api.SistemaEscolar_back.domain.Professor;
import com.api.SistemaEscolar_back.domain.Turma;
import com.api.SistemaEscolar_back.repositories.AlunoRepository;
import com.api.SistemaEscolar_back.repositories.NotaRepository;
import com.api.SistemaEscolar_back.repositories.ProfessorRepository;
import com.api.SistemaEscolar_back.repositories.TurmaRepository;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TurmaRepository turmaRepository;
    
    @Autowired
    private NotaRepository notaRepository;


    //Método de Injeção de Dados no Banco.
    public void instanciaDB(){

       


        Professor pf1 = new Professor(null, "Albaniag", "620.183.150-99","al.baniang@gmail.com","albinha321", "Ciência da computação");
        Professor pf2 = new Professor(null, "Handel", "116.950.590-26","handel.almeidag@gmail.com","handellednah", "Redes de computadores");

        Turma tr1 = new Turma(null, "Javeleiro avançado", pf2);


        Aluno alu1 = new Aluno(null, "kerolen", "664.289.720-00","kerolen.almeidag@gmail.com","kerolenn11", tr1, null);
        Aluno alu2 = new Aluno(null, "Rayanna", "257.881.280-21","Rayanna.almeidag@gmail.com","Rayanna123456", tr1, null);
   
        Nota nt1 = new Nota(null, "10", null);
       
        professorRepository.saveAll(Arrays.asList(pf1, pf2));
        turmaRepository.saveAll(Arrays.asList(tr1));
        alunoRepository.saveAll(Arrays.asList(alu1, alu2));
        notaRepository.saveAll(Arrays.asList(nt1));

    }
}