package com.api.SistemaEscolar_back.domain.dtos;

import java.io.Serializable;

import com.api.SistemaEscolar_back.domain.Nota;

public class NotaDTO implements Serializable {
	
	  private static final long serialVersionUID = 1L;

	    protected Integer id;

	    protected String nota;
	    
	    protected Integer aluno_id;

	    	   	    
		public NotaDTO(Nota nota) {
			this.id = nota.getId();
			this.nota = nota.getNota();
			this.aluno_id = nota.getAluno_id();
		}

		public NotaDTO(Integer id, String nota, Integer aluno_id) {
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
