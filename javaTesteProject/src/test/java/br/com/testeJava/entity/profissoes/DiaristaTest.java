package br.com.testeJava.entity.profissoes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DiaristaTest {
	public static Diarista diarista;
	
	@BeforeAll
	public static void beforeAll() {
		diarista = new Diarista();
	}
	
	@Test
	public void aProfissaoEhApresentavel() {
		Assertions.assertEquals(String.format("Sou um(a) %s", Diarista.class.getSimpleName()), diarista.apresentarProfissao());
	}
}
