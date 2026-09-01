package br.com.testeJava.dao.testeTransacaoConcorrentes;

import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.ImovelDAOQualifier;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;

import javax.ejb.Stateless;

@ImovelDAOQualifier
@Stateless
public class ImovelDAO extends BaseDAO<Imovel> {
}
