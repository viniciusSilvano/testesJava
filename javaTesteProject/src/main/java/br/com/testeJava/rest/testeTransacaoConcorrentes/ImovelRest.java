package br.com.testeJava.rest.testeTransacaoConcorrentes;


import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.testeTransacaoConcorrentes.ImovelBo;
import br.com.testeJava.dto.testeTransacaoConcorrentes.ImovelChecarAlteracaoDto;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;
import br.com.testeJava.rest.BaseRest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/teste/imovel")
public class ImovelRest extends BaseRest {

    @Inject
    private ImovelBo imovelBo;

    @Override
    protected BaseService getService() {
        return imovelBo;
    }

    protected ImovelBo getServiceImpl() {
        return (ImovelBo) imovelBo;
    }

    @POST
    public Response salvar(Imovel imovel){
        try{
            //no código real, devo levar esta chamada para o service, dentro da transação required
            //this.simularOutrosSalvamentos(imovel);
            ImovelChecarAlteracaoDto imovelTela =  new ImovelChecarAlteracaoDto(imovel);
            Imovel imovelSalvo = getServiceImpl().salvar(imovel);

            return Response.ok().entity(parseObjectToStringJSON(imovelSalvo)).build();
        }catch (Exception e){
            return Response.serverError().entity(ExceptionUtils.getStackTrace(e)).build();
        }
    }

    @POST
    @Path("salvar-2")
    public Response salvarDois(Imovel imovel){
        try{
            //no código real, devo levar esta chamada para o service, dentro da transação required
            //this.simularOutrosSalvamentos(imovel);
            ImovelChecarAlteracaoDto imovelTela =  new ImovelChecarAlteracaoDto(imovel);
            Imovel imovelSalvo = getServiceImpl().salvarDois(imovel);

            return Response.ok().entity(parseObjectToStringJSON(imovelSalvo)).build();
        }catch (Exception e){
            return Response.serverError().entity(ExceptionUtils.getStackTrace(e)).build();
        }
    }

    @POST
    @Path("teste-registro-erro/{id}")
    public Response salvarRegistroErroImovel(@PathParam("id") Long id){
        try{
            this.imovelBo.salvarComHistoricoErro(id);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().entity(ExceptionUtils.getStackTrace(e)).build();
        }
    }

    @GET
    public Response recuperarImoveis(){
        try{
            return Response.ok().entity(parseListToJsonString(this.getServiceImpl().getImovelComCibs())).build();
        } catch (Exception e) {
            return Response.serverError().entity(ExceptionUtils.getStackTrace(e)).build();
        }
    }

    @GET
    @Path("/cibs")
    public Response recuperarCibs(){
        try{
            return Response.ok().entity(parseListToJsonString(this.getServiceImpl().getCibs())).build();
        } catch (Exception e) {
            return Response.serverError().entity(ExceptionUtils.getStackTrace(e)).build();
        }
    }

    @GET
    @Path("/count")
    public Response contagemImoveis(){
        try{
            return Response.ok().entity(this.getServiceImpl().getContagemImoveis()).build();
        } catch (Exception e) {
            return Response.serverError().entity(ExceptionUtils.getStackTrace(e)).build();
        }
    }

    @DELETE
    public Response removeAll(){
        try{
            this.imovelBo.removeAll();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(ExceptionUtils.getStackTrace(e)).build();
        }
    }

    public void simularOutrosSalvamentos(Imovel imovel) {
        imovelBo.simularOutrosSalvamentos(imovel);
    }

}
