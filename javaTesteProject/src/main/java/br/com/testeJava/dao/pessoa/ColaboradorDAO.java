package br.com.testeJava.dao.pessoa;

import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.pessoa.qualifier.ColaboradorDAOQualifier;
import br.com.testeJava.entity.pessoa.Colaborador;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;


@ColaboradorDAOQualifier
@Stateless
public class ColaboradorDAO extends BaseDAO<Colaborador> {

	public enum OPCOES_CRITERIA {COLABORADOR,PROFISSAO,COLABORADOR_PROFISSAO}

	public void atualizarNomePorId(int id, String nome) {
		String query = "update TB_COLABORADOR set nome = :nome where id = :id ";
		getEntityManager().createNativeQuery(query).executeUpdate();
	}
	
	public void pesquisaPorCriteria(OPCOES_CRITERIA opcao) {
		
	}

	public List<Colaborador> listAllWithCriteria(Optional<String> nome) {
		// 1. Get the CriteriaBuilder from the EntityManager
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

		// 2. Create a CriteriaQuery object for your specific Entity class
		CriteriaQuery<Colaborador> cq = cb.createQuery(Colaborador.class);

		// 3. Define the FROM clause (Root) of the query
		Root<Colaborador> entidadeColaborador = cq.from(Colaborador.class);

		// 4. Define the SELECT clause (Select * from Product)
		CriteriaQuery<Colaborador> selectColaborador = cq.select(entidadeColaborador);

		if(nome.isPresent()){
			Predicate nomeExato = cb.equal(entidadeColaborador.get("nome"), nome.get());
			Predicate nomeParcial = cb.like(entidadeColaborador.get("nome"),String.format("%%%s%%",nome.get()));
			Predicate whereCompleto = cb.or(nomeExato,nomeParcial);
			selectColaborador.where(whereCompleto);
		}

		// 5. Create and execute the typed query
		TypedQuery<Colaborador> allQuery = getEntityManager().createQuery(selectColaborador);
		return allQuery.getResultList();
	}

}
