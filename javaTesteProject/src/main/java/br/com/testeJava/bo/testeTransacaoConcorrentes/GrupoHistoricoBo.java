package br.com.testeJava.bo.testeTransacaoConcorrentes;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.GrupoHistoricoDao;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.GrupoHistoricoDAOQualifier;
import br.com.testeJava.entity.testeTransacaoConcorrentes.GrupoHistorico;
import br.com.testeJava.entity.testeTransacaoConcorrentes.HistoricoCibCar;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class GrupoHistoricoBo extends BaseService<GrupoHistorico> {

    @Inject
    @GrupoHistoricoDAOQualifier
    private GrupoHistoricoDao grupoHistoricoDao;

    @Override
    protected BaseDAO<GrupoHistorico> getDAO() {
        return grupoHistoricoDao;
    }

    private GrupoHistoricoDao getDAOImpl(){
        return (GrupoHistoricoDao) getDAO();
    }

    public void adicionarHistoricoErro(Imovel imovelDb){
        criarHistorico(imovelDb,"Erro histórico");
    }

    private void criarHistorico(Imovel imovelDb,String mensagem) {
        HistoricoCibCar historicoCibCar = new HistoricoCibCar();
        historicoCibCar.setMensagem(mensagem);
        historicoCibCar.setIrregular(true);
        historicoCibCar.setImovel(imovelDb);

        GrupoHistorico grupoHistorico = new GrupoHistorico();
        grupoHistorico.adicionarHistorico(historicoCibCar);
        imovelDb.adicionarGrupoHistorico(grupoHistorico);
    }

    public List<GrupoHistorico> recuperarPorImovel(Long idImovel){
        return this.getDAOImpl().recuperarPorImovel(idImovel);
    }

    public void adicionarHistorico(Imovel imovelDb) {
        criarHistorico(imovelDb,"Histórico comum");
    }
}
