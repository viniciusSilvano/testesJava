package br.com.testeJava.dao.pessoa;

import javax.ejb.Stateless;

import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.pessoa.qualifier.ColaboradorDAOQualifier;
import br.com.testeJava.entity.pessoa.Colaborador;


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

}
