package br.com.testeJava.bo.testeTransacaoConcorrentes;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.CibDAOQualifier;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cib;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CibBo extends BaseService<Cib> {


    @Inject
    @CibDAOQualifier
    private BaseDAO<Cib> dao;

    @Override
    protected BaseDAO<Cib> getDAO() {
        return dao;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveEntityManager(Cib cib) {
        dao.saveEntityManager(cib);
    }

    public void flush() {
        dao.flush();
    }

    public void detach(Cib cib){
        this.getDAO().detach(cib);
    }

    public List<Cib> buscarCibsPorIdImovel(Long idImovel){
        return this.getDAO().buscarCibsPorIdImovel(idImovel);
    }

}
