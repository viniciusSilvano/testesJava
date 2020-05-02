package br.com.testeJava.entity.profissoes;

import br.com.testeJava.entity.enuns.ProfissaoEnum;

public class Programador extends Profissao{

	@Override
	public ProfissaoEnum getFuncao() {
		return ProfissaoEnum.PROGRAMADOR;
	}
	
}
