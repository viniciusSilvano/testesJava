package br.com.testeJava.dao.testeTransacaoConcorrentes;


import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.CibDAOQualifier;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.CidadeDAOQualifier;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cib;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cidade;

import javax.ejb.Stateless;

@CidadeDAOQualifier
@Stateless
public class CidadeDAO extends BaseDAO<Cidade> {
}
