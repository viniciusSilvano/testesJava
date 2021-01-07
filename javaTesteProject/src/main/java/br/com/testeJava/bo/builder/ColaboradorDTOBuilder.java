package br.com.testeJava.bo.builder;

import java.util.Objects;

import br.com.testeJava.dto.ColaboradorDTO;

public class ColaboradorDTOBuilder {
	private Long id;
	private String nome;
	private static ColaboradorDTOBuilder instance;
	
	private ColaboradorDTOBuilder() {
		
	}
	
	public static ColaboradorDTOBuilder getInstance() {
		if(Objects.nonNull(instance)) {
			return instance;
		}else {
			return instance = new ColaboradorDTOBuilder();
		}
	}
	
	public ColaboradorDTOBuilder nome(String nome) {
		this.nome = nome;
		return instance;
	}
	
	public ColaboradorDTOBuilder id(Long id) {
		this.id = id;
		return instance;
	}
	
	public ColaboradorDTO build() {
		ColaboradorDTO dto = new ColaboradorDTO();
		dto.setId(this.id);
		dto.setNome(this.nome);
		limpar();
		return dto;
	}
	
	private void limpar() {
		this.id = null;
		this.nome = null;
	}
}
