package br.com.testeJava.entity.testeDesempenho;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
//@Table(name = "TB_ENTIDADE_DE_TESTE_1")
public class EntidadeDeTeste_1 {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Entidade_Teste_1_SEQ")
	//@SequenceGenerator(sequenceName = "Entidade_Teste_1_SEQ", allocationSize = 1, name = "Entidade_Teste_1_SEQ")
	private Long id;
	
	//@Column
	private String nome;
	
	//@OneToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
	private List<EntidadeDeTeste_1_1> entidadesFilhas_1;
	
	//@OneToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
	private List<EntidadeDeTeste_1_2> entidadesFilhas_2;
	
	public EntidadeDeTeste_1() {}

	public EntidadeDeTeste_1(String nome, List<EntidadeDeTeste_1_1> entidadesFilhas_1,
			List<EntidadeDeTeste_1_2> entidadesFilhas_2) {
		super();
		this.nome = nome;
		this.entidadesFilhas_1 = entidadesFilhas_1;
		this.entidadesFilhas_2 = entidadesFilhas_2;
	}

	public List<EntidadeDeTeste_1_1> getEntidadesFilhas_1() {
		return entidadesFilhas_1;
	}

	public void setEntidadesFilhas_1(List<EntidadeDeTeste_1_1> entidadesFilhas_1) {
		this.entidadesFilhas_1 = entidadesFilhas_1;
	}

	public List<EntidadeDeTeste_1_2> getEntidadesFilhas_2() {
		return entidadesFilhas_2;
	}

	public void setEntidadesFilhas_2(List<EntidadeDeTeste_1_2> entidadesFilhas_2) {
		this.entidadesFilhas_2 = entidadesFilhas_2;
	}

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
