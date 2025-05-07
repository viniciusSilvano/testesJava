package br.com.testeJava.dao.testeDesempenho;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;

import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeDesempenho.qualifiers.EntidadeDeTesteDAOQualifier;
import br.com.testeJava.entity.testeDesempenho.EntidadeDeTeste;

@Stateless
@EntidadeDeTesteDAOQualifier
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EntidadeDeTesteDAO extends BaseDAO{

	//@PersistenceContext(unitName = "testeDesempenho")
	//private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		/*EntityManagerFactory factory = Persistence.createEntityManagerFactory("testeDesempenho");
		return factory.createEntityManager();*/
		//return entityManager;
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public EntidadeDeTeste cadastrar(EntidadeDeTeste entidade) {
		//getEntityManager().persist(entidade);
		return entidade;
	}
	
	public Long consultarUltimoId() {
		//StringBuilder sql = new StringBuilder("SELECT e.id FROM EntidadeDeTeste e ORDER BY e.id DESC ");
		//TypedQuery<Long> query = getEntityManager().createQuery(sql.toString(), Long.class);
		//query.setMaxResults(1);
		//try {
			//return query.getSingleResult();
		//}catch(NoResultException e) {
			//return 0L;
		//}
		return 0L;
	}
		
	public List<EntidadeDeTeste> listar() {
		//StringBuilder sql = new StringBuilder("SELECT e FROM EntidadeDeTeste e ");
		//TypedQuery<EntidadeDeTeste> query = getEntityManager().createQuery(sql.toString(), EntidadeDeTeste.class);
		//return query.getResultList();
		return null;
	}
	
	public List<EntidadeDeTeste> listarComFilhas(){
		/*StringBuilder sql = new StringBuilder("SELECT * FROM EntidadeDeTeste e ");
		sql.append("LEFT JOIN e.entidadeFilha ef ");
		sql.append("LEFT JOIN FETCH ef.entidadesFilhas_1 ef1 ");
		sql.append("LEFT JOIN FETCH ef.entidadesFilhas_2 ef2 ");
		TypedQuery<EntidadeDeTeste> query = getEntityManager().createQuery(sql.toString(), EntidadeDeTeste.class);
		return query.getResultList();*/
		return null;
	}

	public EntidadeDeTeste listarPorId(Long id) {
		/*StringBuilder sql = new StringBuilder("SELECT e FROM EntidadeDeTeste e WHERE e.id = :id ");
		TypedQuery<EntidadeDeTeste> query =  getEntityManager().createQuery(sql.toString(), EntidadeDeTeste.class);
		query.setParameter("id", id);
		return query.getSingleResult();*/
		return null;
	}
	
	public EntidadeDeTeste listarPorIdComFilhas(Long id) {
		/*StringBuilder sql = new StringBuilder("SELECT e FROM EntidadeDeTeste e WHERE e.id = :id ");
		sql.append("LEFT JOIN e.entidadeFilha ef ");
		sql.append("LEFT JOIN FETCH ef.entidadesFilhas_1 ef1 ");
		sql.append("LEFT JOIN FETCH ef.entidadesFilhas_2 ef2 ");
		TypedQuery<EntidadeDeTeste> query =  getEntityManager().createQuery(sql.toString(), EntidadeDeTeste.class);
		query.setParameter("id", id);
		return query.getSingleResult();*/
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(EntidadeDeTeste entidade) {
		//getEntityManager().remove(entidade);
	}

	public void cadastrarPorPaiEntidadeDeTeste1(Long idPai) {
		/*StringBuilder sql = new StringBuilder("INSERT INTO EntidadeDeTeste_1 (nome) ");
		sql.append("select CONCAT(pai.nome,:complementoNome) ");
		sql.append("from EntidadeDeTeste pai where pai.id = :idPai ");
		
		String completementoNome = "jujuba";
		
		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("idPai", idPai);
		query.setParameter("complementoNome", completementoNome);
		query.executeUpdate();*/
	}

}
