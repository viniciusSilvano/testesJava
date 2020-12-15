package br.com.testeJava.entity.testeDesempenho;

import br.com.testeJava.entity.IEntidade;

//@Entity
//@Table(name = "TB_ENTIDADE_DE_TESTE")
public class EntidadeDeTeste implements IEntidade {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Entidade_Teste_SEQ")
	//@SequenceGenerator(sequenceName = "Entidade_Teste_SEQ", allocationSize = 1, name = "Entidade_Teste_SEQ")
	private Long id;
	
	//@Column
	private String nome;
	
	//@OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
	private EntidadeDeTeste_1 entidadeFilha;

	public EntidadeDeTeste() {}
	
	public EntidadeDeTeste(String nome, EntidadeDeTeste_1 entidadeFilha) {
		super();
		this.nome = nome;
		this.entidadeFilha = entidadeFilha;
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

	public EntidadeDeTeste_1 getEntidadeFilha() {
		return entidadeFilha;
	}

	public void setEntidadeFilha(EntidadeDeTeste_1 entidadeFilha) {
		this.entidadeFilha = entidadeFilha;
	}
}
