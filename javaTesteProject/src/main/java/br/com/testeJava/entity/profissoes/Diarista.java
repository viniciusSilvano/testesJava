package br.com.testeJava.entity.profissoes;

import br.com.testeJava.entity.enuns.ProfissaoEnum;

public class Diarista extends Profissao {

	public Diarista() {}
	
	public Diarista(String nome, int idade) {
		super(nome,idade);
	}
	
	@Override
	public ProfissaoEnum getFuncao() {
		return ProfissaoEnum.DIARISTA;
	}
}
