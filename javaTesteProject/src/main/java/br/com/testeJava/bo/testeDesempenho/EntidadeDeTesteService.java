package br.com.testeJava.bo.testeDesempenho;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.testeDesempenho.qualifiers.EntidadeDeTesteServiceQualifier;
import br.com.testeJava.dao.testeDesempenho.BaseDAO;
import br.com.testeJava.dao.testeDesempenho.EntidadeDeTesteDAO;
import br.com.testeJava.dao.testeDesempenho.qualifiers.EntidadeDeTesteDAOQualifier;
import br.com.testeJava.entity.testeDesempenho.EntidadeDeTeste;

@Stateless
@EntidadeDeTesteServiceQualifier
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EntidadeDeTesteService extends BaseService {
	
	@Inject
	@EntidadeDeTesteDAOQualifier
	private BaseDAO dao;
	
	@Override
	protected BaseDAO getDAO() {
		return dao;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarEntidadeDeTeste(EntidadeDeTeste entidade) {
		((EntidadeDeTesteDAO) dao).cadastrar(entidade);
	}
}
