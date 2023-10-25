package br.com.testeJava.dao.pessoa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.testeJava.dao.IDAO;
import br.com.testeJava.dao.pessoa.qualifier.ColaboradorDAOQualifier;


@ColaboradorDAOQualifier
@Stateless
public class ColaboradorDAO implements IDAO {

	public enum OPCOES_CRITERIA {COLABORADOR,PROFISSAO,COLABORADOR_PROFISSAO}
	
	@PersistenceContext(unitName = "javaTesteProject")
	EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void atualizarNomePorId(int id, String nome) {
		String query = "update TB_COLABORADOR set nome = :nome where id = :id ";
		getEntityManager().createNativeQuery(query).executeUpdate();
	}
	
	public void pesquisaPorCriteria(OPCOES_CRITERIA opcao) {
		
	}

}
