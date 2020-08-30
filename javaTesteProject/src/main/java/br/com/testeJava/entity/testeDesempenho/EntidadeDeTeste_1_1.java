package br.com.testeJava.entity.testeDesempenho;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "TB_ENTIDADE_DE_TESTE_1_1")
public class EntidadeDeTeste_1_1 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Entidade_Teste_1_1_SEQ")
    @SequenceGenerator(sequenceName = "Entidade_Teste_1_1_SEQ", allocationSize = 1, name = "Entidade_Teste_1_1_SEQ")
	private Long id;
	
}
