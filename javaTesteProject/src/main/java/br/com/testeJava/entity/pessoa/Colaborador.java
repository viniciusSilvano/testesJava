package br.com.testeJava.entity.pessoa;

import java.util.List;

import br.com.testeJava.entity.profissoes.Profissao;


public class Colaborador implements Pessoa {
	private Long id;
	private List<Profissao> profissoes;

	public Colaborador() {}
	
	public Colaborador(Long id, List<Profissao> profissoes) {
		super();
		this.id = id;
		this.profissoes = profissoes;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
	public List<Profissao> getProfissoes() {
		return profissoes;
	}


	public void setProfissoes(List<Profissao> profissoes) {
		this.profissoes = profissoes;
	}

	@Override
	public String toString() {
		return "Colaborador [id=" + id + ", profissoes=" + profissoes + "]";
	}
}
