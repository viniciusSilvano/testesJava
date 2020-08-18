package br.com.testeJava.bo.testeDatas;

public class Main {

	public static void main(String[] args) {
		TestExecuter[] testes = {new ComparacaoSegundos()};
		for(TestExecuter teste : testes) {
			teste.execute();
		}
	}

}
