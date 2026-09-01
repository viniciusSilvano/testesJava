package br.com.testeJava.dao.testeTransacaoConcorrentes;


import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.CibDAOQualifier;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cib;

import javax.ejb.Stateless;

@CibDAOQualifier
@Stateless
public class CibDAO extends BaseDAO<Cib> {
}
