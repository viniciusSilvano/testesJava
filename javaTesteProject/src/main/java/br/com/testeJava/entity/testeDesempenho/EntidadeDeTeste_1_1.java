package br.com.testeJava.entity.testeDesempenho;

//@Entity
//@Table(name = "TB_ENTIDADE_DE_TESTE_1_1")
public class EntidadeDeTeste_1_1 {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Entidade_Teste_1_1_SEQ")
	//@SequenceGenerator(sequenceName = "Entidade_Teste_1_1_SEQ", allocationSize = 1, name = "Entidade_Teste_1_1_SEQ")
	private Long id;
	
	//@Column
	private String nome;

	public EntidadeDeTeste_1_1() {}
	
	public EntidadeDeTeste_1_1(String nome) {
		super();
		this.nome = nome;
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
