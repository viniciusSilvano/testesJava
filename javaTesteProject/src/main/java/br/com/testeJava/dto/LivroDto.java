package br.com.testeJava.dto;

import javax.ejb.Stateless;

import br.com.testeJava.bo.infinispan.cache.Cacheavel;

@Stateless
public class LivroDto implements Cacheavel {

	/**
	 * 
	 */
	//private static final long serialVersionUID = -6958223360419716648L;
	private Long id; 
	private String nome;
	
	public LivroDto() {}
	
	public LivroDto(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public LivroDto(String nome) {
		super();
		this.nome = nome;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
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
