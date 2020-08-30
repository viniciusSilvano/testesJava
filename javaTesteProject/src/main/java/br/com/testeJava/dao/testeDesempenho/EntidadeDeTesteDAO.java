package br.com.testeJava.dao.testeDesempenho;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.testeJava.dao.testeDesempenho.qualifiers.EntidadeDeTesteDAOQualifier;
import br.com.testeJava.entity.testeDesempenho.EntidadeDeTeste;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Dependent
@EntidadeDeTesteDAOQualifier
public class EntidadeDeTesteDAO implements IDAO{

	@PersistenceContext(unitName = "testeDesempenho")
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public EntidadeDeTeste cadastrar(EntidadeDeTeste entidade) {
		getEntityManager().persist(entidade);
		return entidade;
	}

	public List<EntidadeDeTeste> listar() {
		StringBuilder sql = new StringBuilder("SELECT e FROM EntidadeDeTeste e ");
		TypedQuery<EntidadeDeTeste> query = getEntityManager().createNamedQuery(sql.toString(), EntidadeDeTeste.class);
		return query.getResultList();
	}

	public EntidadeDeTeste listarPorId(Long id) {
		StringBuilder sql = new StringBuilder("SELECT e FROM EntidadeDeTeste e WHERE e.id = :id ");
		TypedQuery<EntidadeDeTeste> query =  getEntityManager().createNamedQuery(sql.toString(), EntidadeDeTeste.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(EntidadeDeTeste entidade) {
		getEntityManager().remove(entidade);
	}

}
