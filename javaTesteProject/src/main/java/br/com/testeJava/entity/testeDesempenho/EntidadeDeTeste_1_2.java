package br.com.testeJava.entity.testeDesempenho;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ENTIDADE_DE_TESTE_1_2")
public class EntidadeDeTeste_1_2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Entidade_Teste_1_2_SEQ")
    @SequenceGenerator(sequenceName = "Entidade_Teste_1_2_SEQ", allocationSize = 1, name = "Entidade_Teste_1_2_SEQ")
	private Long id;
	
	@Column
	private String nome;
	
	public EntidadeDeTeste_1_2() {}

	public EntidadeDeTeste_1_2(String nome) {
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