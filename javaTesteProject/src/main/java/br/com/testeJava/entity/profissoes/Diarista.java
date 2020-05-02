package br.com.testeJava.entity.profissoes;

import br.com.testeJava.entity.enuns.ProfissaoEnum;

public class Diarista extends Profissao {

	@Override
	public ProfissaoEnum getFuncao() {
		return ProfissaoEnum.DIARISTA;
	}
}
