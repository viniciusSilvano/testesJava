package br.com.testeJava.entity.profissoes;

import org.junit.jupiter.api.*;

public class ProgramadorTest {
	
	public static Programador programador;
	@BeforeAll
	public static void beforeAll() {
		programador = new Programador();
	}
	
	@Test
	public void aProfissaoEhApresentavel() {
		Assertions.assertEquals(String.format("Sou um(a) %s", Programador.class.getSimpleName()), programador.apresentarProfissao());
	}
}
