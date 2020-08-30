package br.com.testeJava.bo.profissoes;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.profissoes.qualifier.ProfissaoServiceQualifier;
import br.com.testeJava.dao.testeDesempenho.IDAO;
import br.com.testeJava.entity.profissoes.Diarista;
import br.com.testeJava.entity.profissoes.Jardineiro;
import br.com.testeJava.entity.profissoes.Profissao;
import br.com.testeJava.entity.profissoes.Programador;

@Stateless
@ProfissaoServiceQualifier
public class ProfissaoService extends BaseService {
	private static final List<Profissao> dbMock;
	
	static {
		dbMock = new ArrayList<Profissao>();
		dbMock.add(new Programador("Juninho",20));
		dbMock.add(new Jardineiro("Larissa",22));
		dbMock.add(new Diarista("Hildete", 45));
	}
	
	@Override
	protected IDAO getDAO() {
		return null;
	}
	
	public List<? extends Profissao> recuperarProfissoes() {
		return dbMock;
	}
	
	public void exercerFuncao(Profissao profissao) {
		
	}
}
