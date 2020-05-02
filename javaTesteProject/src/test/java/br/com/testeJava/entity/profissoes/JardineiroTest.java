package br.com.testeJava.entity.profissoes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JardineiroTest {
	public static Jardineiro jardineiro;
	@BeforeAll
	public static void beforeAll() {
		jardineiro = new Jardineiro();
	}
	
	@Test
	public void aProfissaoEhApresentavel() {
		Assertions.assertEquals(String.format("Sou um(a) %s", Jardineiro.class.getSimpleName()), jardineiro.apresentarProfissao());
	}
}
