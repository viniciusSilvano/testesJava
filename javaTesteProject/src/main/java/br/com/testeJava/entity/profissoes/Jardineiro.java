package br.com.testeJava.entity.profissoes;

import br.com.testeJava.entity.enuns.ProfissaoEnum;

public class Jardineiro extends Profissao{

	public Jardineiro(String nome, int idade) {
		super(nome,idade);
	}
	
	@Override
	public ProfissaoEnum getFuncao() {
		return ProfissaoEnum.JARDINEIRO;
	}

}
