package br.com.testeJava.rest.testeTransacaoConcorrentes;


import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.testeTransacaoConcorrentes.HistoricoCibCarBo;
import br.com.testeJava.bo.testeTransacaoConcorrentes.ImovelBo;
import br.com.testeJava.dto.testeTransacaoConcorrentes.ImovelChecarAlteracaoDto;
import br.com.testeJava.entity.testeTransacaoConcorrentes.HistoricoCibCar;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;
import br.com.testeJava.rest.BaseRest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/teste/historico-cib-car")
public class HistoricoCarCibRest extends BaseRest<HistoricoCibCar> {

    @Inject
    private HistoricoCibCarBo historicoCibCarBo;

    @Override
    protected BaseService<HistoricoCibCar> getService() {
        return historicoCibCarBo;
    }

    protected HistoricoCibCarBo getServiceImpl() {
        return historicoCibCarBo;
    }

    @POST
    @Path("/teste-lote")
    public Response adicionarHistoricoPaiEFilho(){
        try {
            this.getServiceImpl().adicionarHistoricoPaiEFilho();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/teste-lista-filho")
    public Response listarHistoricosCarComFilhos(){
        try {
            List<HistoricoCibCar> historicosCibCar = this.getServiceImpl().listarHistoricosCarComFilhos();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/teste-lista-pai")
    public Response listarHistoricosCarComPai(){
        try {
            List<HistoricoCibCar> historicosCibCar = this.getServiceImpl().listarHistoricosCarComPai();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/teste-lista")
    public Response listarHistoricosCar(){
        try {
            List<HistoricoCibCar> historicosCibCar = this.getServiceImpl().listarHistoricosCar();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/teste-lista-sem-pai")
    public Response listarHistoricosCarCibSemPai(){
        try {
            List<HistoricoCibCar> historicosCibCar = this.getServiceImpl().listarHistoricosCarCibSemPai();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/teste-irregularidade-historico")
    public Response verificarSeHistoricoEhIrregular(){
        try {
            this.getServiceImpl().verificarSeHistoricoEhIrregular();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
