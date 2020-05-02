package br.com.testeJava.entity.profissoes;

import br.com.testeJava.entity.enuns.ProfissaoEnum;

public class Programador extends Profissao{
	
	public Programador(String nome, int idade) {
		super(nome,idade);
	}
	
	@Override
	public ProfissaoEnum getFuncao() {
		return ProfissaoEnum.PROGRAMADOR;
	}
	
}
