package br.com.testeJava.bo.profissoes;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProfissaoServiceTest {
	
	public static ProfissaoService profissaoService;
	
	@BeforeAll
	public static void beforeAll() {
		profissaoService = new ProfissaoService();
	}
	
	@Test
	public void profissoesListIsAList() {
		Assumptions.assumeTrue(profissaoService.recuperarProfissoes() instanceof List, "recuperarProfissoes must return a List");
	}
	
	@Test
	public void profissoesListIsNotNull() {
		Assertions.assertNotNull(profissaoService.recuperarProfissoes());
	}
}
