package br.com.testeJava.bo.profissoes;

import java.util.ArrayList;
import java.util.List;

import br.com.testeJava.entity.profissoes.Diarista;
import br.com.testeJava.entity.profissoes.Jardineiro;
import br.com.testeJava.entity.profissoes.Profissao;
import br.com.testeJava.entity.profissoes.Programador;

public class ProfissaoService {
	private static final List<Profissao> dbMock;
	
	static {
		dbMock = new ArrayList<Profissao>();
		dbMock.add(new Programador());
		dbMock.add(new Jardineiro());
		dbMock.add(new Diarista());
	}
	
	public List<Profissao> recuperarProfissoes() {
		return dbMock;
	}
	
	public void exercerFuncao(Profissao profissao) {
				
	}
}
