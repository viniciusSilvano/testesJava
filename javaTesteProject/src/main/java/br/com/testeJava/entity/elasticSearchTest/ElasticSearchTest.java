package br.com.testeJava.entity.elasticSearchTest;

import br.com.testeJava.entity.IEntidade;

public class ElasticSearchTest implements IEntidade {
	private Long id;
	private String nome;
		
	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}
