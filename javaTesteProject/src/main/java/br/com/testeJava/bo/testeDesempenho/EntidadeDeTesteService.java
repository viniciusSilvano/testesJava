package br.com.testeJava.bo.testeDesempenho;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.testeDesempenho.qualifiers.EntidadeDeTesteServiceQualifier;
import br.com.testeJava.dao.testeDesempenho.EntidadeDeTesteDAO;
import br.com.testeJava.dao.testeDesempenho.IDAO;
import br.com.testeJava.dao.testeDesempenho.qualifiers.EntidadeDeTesteDAOQualifier;
import br.com.testeJava.entity.testeDesempenho.EntidadeDeTeste;

@Stateless
@Dependent
@EntidadeDeTesteServiceQualifier
public class EntidadeDeTesteService extends BaseService {
	
	@Inject
	@EntidadeDeTesteDAOQualifier
	private IDAO dao;
	
	@Override
	protected IDAO getDAO() {
		return dao;
	}
	
	public void cadastrarEntidadeDeTeste(EntidadeDeTeste entidade) {
		((EntidadeDeTesteDAO) this.getDAO()).cadastrar(entidade);
	}

}
