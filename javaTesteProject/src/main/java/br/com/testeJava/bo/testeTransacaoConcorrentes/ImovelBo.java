package br.com.testeJava.bo.testeTransacaoConcorrentes;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.ImovelDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.ImovelDAOQualifier;
import br.com.testeJava.dto.testeTransacaoConcorrentes.CibDto;
import br.com.testeJava.dto.testeTransacaoConcorrentes.GrupoHistoricoDto;
import br.com.testeJava.dto.testeTransacaoConcorrentes.HistoricoDto;
import br.com.testeJava.dto.testeTransacaoConcorrentes.ImovelRespostaDto;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cib;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cidade;
import br.com.testeJava.entity.testeTransacaoConcorrentes.GrupoHistorico;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;
import br.com.testeJava.interceptor.TimeInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Stateless
public class ImovelBo extends BaseService<Imovel> {

    @Inject
    private ImovelBo self;

    @Inject
    private CidadeBo cidadeBo;

    @Inject
    @ImovelDAOQualifier
    private BaseDAO<Imovel> dao;

    @Inject
    private CibBo cibBo;

    @Inject
    private GrupoHistoricoBo grupoHistoricoBo;

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


    @Interceptors(TimeInterceptor.class)
    public Imovel salvarDois(Imovel imovel){
        Imovel imovelDb = null;
        if (imovel.getId() == null) {

            List<Cib> listaFilhos = imovel.getCibs();

            umMetodoNotSupported(listaFilhos);
            getDaoImpl().saveEntityManager(imovel);
            getDaoImpl().flush();

            atualizarCibs(listaFilhos,imovel.getId());

        }else {

            imovelDb = getDaoImpl().get(Imovel.class, imovel.getId());

            imovelDb.setNome(imovel.getNome());
            imovelDb.setCar(imovel.getCar());
            imovelDb.setCidade(imovel.getCidade());

            getDAO().merge(imovelDb);
        }
        return imovelDb;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Imovel salvarTres(Imovel imovel){
        Imovel imovelBd = getDaoImpl().getReference(Imovel.class,imovel.getId());
        List<Cib> cibsBd = cibBo.buscarCibsPorIdImovel(imovel.getId());
        for(Cib cibTela : imovel.getCibs()) {

            if(cibTela.getId() != null){
                Optional<Cib> cibBdOpt = cibsBd.stream().filter(cibTelaAFiltrar -> cibTelaAFiltrar.getId().equals(cibTela.getId())).findFirst();
                if(cibBdOpt.isPresent()){
                    cibTela.setSituacaoCib(cibBdOpt.get().getSituacaoCib());
                    getDaoImpl().atualizarCibsBulkUpdate(cibTela);
                }else{
                    throw new IllegalStateException();
                }
            }else{
                Cib cib = new Cib();
            }

        }
        return getDaoImpl().get(Imovel.class,imovel.getId());
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void umMetodoNotSupported(List<Cib> listaFilhos) {
        listaFilhos.size();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarCibs(List<Cib> listaFilhos, Long id) {
        Imovel imovelDb =  getDAO().get(Imovel.class,id);

        AtomicInteger contagem = new AtomicInteger();


        atualizarCib(listaFilhos,imovelDb,contagem);

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarCib(List<Cib> listaFilhos,Imovel imovelDb,AtomicInteger contagem){
        for(Cib cib: listaFilhos) {
            try {
                if (contagem.get() == 0) {
                    contagem.incrementAndGet();
                    throw new RuntimeException("teste");
                }
                cib.setImovel(imovelDb);
                grupoHistoricoBo.adicionarHistorico(imovelDb);
            } catch (Exception e) {
                grupoHistoricoBo.adicionarHistoricoErro(imovelDb);
            }
        }

        getDAO().merge(imovelDb);
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

    public List<ImovelRespostaDto> getImovelResultante(){
        List<Imovel> imoveis = this.getDaoImpl().getImovelComCibs();

        List<ImovelRespostaDto> imoveisDto = imoveis.stream().map(imovelEntity -> {
            ImovelRespostaDto imovelRespostaDto = new ImovelRespostaDto(imovelEntity);
            imovelRespostaDto.setCibs(imovelEntity.getCibs().stream().map(CibDto::new).collect(Collectors.toList()));
            return imovelRespostaDto;
        }).collect(Collectors.toList());

        for(ImovelRespostaDto imovelRespostaDto : imoveisDto) {
            List<GrupoHistorico> grupoHistoricos = this.grupoHistoricoBo.recuperarPorImovel(imovelRespostaDto.getId());
            imovelRespostaDto.setGrupos(grupoHistoricos.stream().map(grupoHistorico -> {
                GrupoHistoricoDto grupoHistoricoDto = new GrupoHistoricoDto(grupoHistorico);
                grupoHistoricoDto.setHistoricos(grupoHistorico.getHistoricos().stream().map(HistoricoDto::new).collect(Collectors.toList()));
                return grupoHistoricoDto;
            }).collect(Collectors.toList()));
        }

        return imoveisDto;
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
