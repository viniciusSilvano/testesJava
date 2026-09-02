package br.com.testeJava.bo.testeTransacaoConcorrentes;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.CidadeDAOQualifier;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cidade;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CidadeBo extends BaseService<Cidade> {

    @Inject
    @CidadeDAOQualifier
    private BaseDAO<Cidade> cidadeBaseDAO;

    @Override
    protected BaseDAO<Cidade> getDAO() {
        return cidadeBaseDAO;
    }

    public void saveEntityManager(Cidade cidade){
        getDAO().save(cidade);
    }

    public void flush(){
        this.getDAO().flush();
    }
}
