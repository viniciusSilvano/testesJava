package br.com.testeJava.bo.testeTransacaoConcorrentes;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.ImovelDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.ImovelDAOQualifier;
import br.com.testeJava.dto.testeTransacaoConcorrentes.ImovelChecarAlteracaoDto;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cib;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cidade;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ImovelBo extends BaseService<Imovel> {

    @Inject
    private ImovelBo self;

    @Inject
    private CidadeBo cidadeBo;

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
        boolean executarCib = false;
        boolean executarCar = false;

        Imovel imovelDb = null;
        if (imovel.getId() == null) {

            for (Cib cib : imovel.getCibs()) {
                cib.setImovel(imovel);
            }

            getDaoImpl().save(imovel.getCidade());

            imovel.setId((Long) getDaoImpl().save(imovel));
            getDaoImpl().flush();

            imovelDb  = imovel;
        }else {

            imovelDb = getDaoImpl().get(Imovel.class, imovel.getId());

            imovelDb.setNome(imovel.getNome());
            imovelDb.setCar(imovel.getCar());
            imovelDb.setCidade(imovel.getCidade());

            // Remove CIBs que não vieram mais da tela
            imovelDb.getCibs().removeIf(cibDb ->
                    imovel.getCibs().stream()
                            .noneMatch(cibTela ->
                                    cibTela.getId() != null &&
                                            cibTela.getId().equals(cibDb.getId())
                            )
            );

            // Adiciona novos e atualiza existentes
            for (Cib cibTela : imovel.getCibs()) {

                if (cibTela.getId() == null) {

                    imovelDb.adicionarCib(cibTela);

                } else {

                    Cib cibDb = imovelDb.getCibs().stream()
                            .filter(cib -> cib.getId().equals(cibTela.getId()))
                            .findFirst()
                            .orElseThrow(() ->
                                    new IllegalStateException(
                                            "CIB não pertence ao imóvel: " + cibTela.getId()
                                    )
                            );

                    cibDb.setNumeroCib(cibTela.getNumeroCib());
                    cibDb.setSituacaoCib(cibTela.getSituacaoCib());
                }
            }

            getDaoImpl().saveOrUpdate(imovelDb);
            getDaoImpl().flush();
        }


        atualizarSituacaoCibs(imovelDb);
        atualizarSituacaoCar(imovelDb);

        getDaoImpl().saveOrUpdate(imovelDb);
        getDaoImpl().flush();

        /*if(executarCar && executarCib){
            atualizarSituacaoCibs(imovel);
            atualizarSituacaoCar(imovel);
        }else if(executarCib){
            atualizarSituacaoCibs(imovel);
        }else if(executarCar){
            atualizarSituacaoCar(imovel);
        }*/

        //no código real, devo trazer essa chamada que era do rest para cá.
        this.simularOutrosSalvamentos(imovelDb);
        return imovelDb;

    }


    public void salvarComHistoricoErro(Long id){
        Imovel imovelDb = getDaoImpl().get(Imovel.class, id);
        if(this.salvarComHistoricoErro2(imovelDb)){
            Cidade cidade = new Cidade();
            cidade.setNome("cidade do erro");
            imovelDb.setCidade(cidade);
            this.getDaoImpl().saveEntityManager(cidade);
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean salvarComHistoricoErro2(Imovel imovel){
        try{
            this.salvar(imovel);
            throw new RuntimeException("Teste erro");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return true;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void verificarSeHouveAlteracaoCarECib(Imovel imovel) {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarSituacaoCar(Imovel imovel) {
        imovel.setSituacaoCar("situação teste car");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarSituacaoCibs(Imovel imovel) {
        for(Cib cib : imovel.getCibs()){
            cib.setSituacaoCib("situação teste cib");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void simularOutrosSalvamentos(Imovel imovel) {
        Cidade cidade = new Cidade();
        cidade.setNome("teste");
        this.cidadeBo.saveEntityManager(cidade);
        this.cidadeBo.flush();
        imovel.setCidade(cidade);
        this.getDaoImpl().saveEntityManager(imovel);
        this.getDaoImpl().flush();
    }

    public List<Imovel> getImovelComCibs(){
        return this.getDaoImpl().getImovelComCibs();
    }

    public Long getContagemImoveis(){
        return this.getDaoImpl().getContagemImoveis();
    }

    public void removeAll() {
        this.getDaoImpl().removeAll();
    }

    public List<Cib> getCibs() {
        return this.getDaoImpl().getCibs();
    }
}
