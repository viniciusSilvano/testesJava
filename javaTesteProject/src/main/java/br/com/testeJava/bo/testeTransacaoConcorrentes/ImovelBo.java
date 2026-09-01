package br.com.testeJava.bo.testeTransacaoConcorrentes;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.pessoa.qualifier.ColaboradorDAOQualifier;
import br.com.testeJava.dao.testeTransacaoConcorrentes.ImovelDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.ImovelDAOQualifier;
import br.com.testeJava.entity.pessoa.Colaborador;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cib;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class ImovelBo extends BaseService<Imovel> {

    @Inject
    private CibBo cibBo;

    @Inject
    private ImovelBo self;

    @Inject
    @ImovelDAOQualifier
    private BaseDAO<Imovel> dao;

    @Override
    protected BaseDAO<Imovel> getDAO() {
        return dao;
    }

    public ImovelDAO getDaoImpl(){
        return (ImovelDAO) this.dao;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Imovel salvar(Imovel imovel){
        for(Cib cib : imovel.getCibs()){
            cibBo.save(cib);
        }
        cibBo.flush();

        this.getDaoImpl().saveEntityManager(imovel);
        this.getDaoImpl().flush();
        atualizarSituacaoCibs(imovel);
        atualizarSituacaoCar(imovel);
        return imovel;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarSituacaoCar(Imovel imovel) {
        imovel.setSituacaoCar("situação teste car");
        this.dao.saveEntityManager(imovel);
        this.dao.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarSituacaoCibs(Imovel imovel) {
        for(Cib cib : imovel.getCibs()){
            cib.setSituacaoCib("situação teste cib");
            cibBo.save(cib);
        }
        cibBo.flush();
    }

}
