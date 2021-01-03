package br.com.testeJava.entity.pessoa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.testeJava.entity.profissoes.Profissao;

@Entity
@PersistenceUnit(name = "javaTesteProject")
@Table(name = "TB_COLABORADOR")
public class Colaborador implements Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COLABORADOR")
	private Long id;
	
	@Transient
	private List<Profissao> profissoes;
	
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "NOME_COLABORADOR")
	private String nome;

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

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
}
