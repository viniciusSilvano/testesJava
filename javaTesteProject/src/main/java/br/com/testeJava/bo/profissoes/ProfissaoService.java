package br.com.testeJava.bo.profissoes;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.Local;
import javax.ejb.Stateful;

import br.com.testeJava.entity.profissoes.Diarista;
import br.com.testeJava.entity.profissoes.Jardineiro;
import br.com.testeJava.entity.profissoes.Profissao;
import br.com.testeJava.entity.profissoes.Programador;

@Stateless
public class ProfissaoService {
	private static final List<Profissao> dbMock;
	
	static {
		dbMock = new ArrayList<Profissao>();
		dbMock.add(new Programador("Juninho",20));
		dbMock.add(new Jardineiro("Larissa",22));
		dbMock.add(new Diarista("Hildete", 45));
	}
	
	public List<? extends Profissao> recuperarProfissoes() {
		return dbMock;
	}
	
	public void exercerFuncao(Profissao profissao) {
		
	}
}
