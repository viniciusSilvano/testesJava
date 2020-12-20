package br.com.testeJava.bo.testeDesempenho;

import java.util.Arrays;
import java.util.List;

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
import br.com.testeJava.entity.testeDesempenho.EntidadeDeTeste_1;
import br.com.testeJava.entity.testeDesempenho.EntidadeDeTeste_1_1;
import br.com.testeJava.entity.testeDesempenho.EntidadeDeTeste_1_2;

@Stateless
@EntidadeDeTesteServiceQualifier
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EntidadeDeTesteService extends BaseService {
	
	@Inject
	@EntidadeDeTesteDAOQualifier
	private BaseDAO dao;
	
	@Override
	protected EntidadeDeTesteDAO getDAO() {
		return ((EntidadeDeTesteDAO)dao);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarEntidadeDeTeste(EntidadeDeTeste entidade) {
		getDAO().cadastrar(entidade);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void realizarMockBD(Long quantidade) {
		Long ultimoId = consultarUltimoId();
		for(int i = 0; i < quantidade.longValue(); i++) {
			ultimoId++;
			cadastrarEntidadeDeTeste(
					new EntidadeDeTeste(gerarNomeMock(ultimoId), 
						new EntidadeDeTeste_1(gerarNomeMock(ultimoId), 
								Arrays.asList(new EntidadeDeTeste_1_1(gerarNomeMock(ultimoId))), 
								Arrays.asList(new EntidadeDeTeste_1_2(gerarNomeMock(ultimoId)))))
			);
		}
	}
	
	public List<EntidadeDeTeste> listar() {
		return getDAO().listar();
	}
	
	public EntidadeDeTeste listarPorId(Long id) {
		return getDAO().listarPorId(id);
	}
	
	public List<EntidadeDeTeste> listarComFilhas(){
		return getDAO().listarComFilhas();
	}
	
	public EntidadeDeTeste listarPorIdComFilhas(Long id){
		return getDAO().listarPorIdComFilhas(id);
	}
	
	public void cadastrarPorPaiEntidadeDeTeste1(Long idPai) {
		getDAO().cadastrarPorPaiEntidadeDeTeste1(idPai);
	}
	
	private Long consultarUltimoId() {
		return getDAO().consultarUltimoId();
	}
		
	private String gerarNomeMock(Long id) {
		return String.format("EntidadeDeTeste_N_%d", id);
	}

}
