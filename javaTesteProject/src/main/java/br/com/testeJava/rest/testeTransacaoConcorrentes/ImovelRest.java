package br.com.testeJava.rest.testeTransacaoConcorrentes;


import br.com.testeJava.bo.BaseService;
import br.com.testeJava.bo.testeTransacaoConcorrentes.ImovelBo;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;
import br.com.testeJava.rest.BaseRest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
            Imovel imovelSalvo = getServiceImpl().salvar(imovel);
            return Response.ok().entity(parseObjectToStringJSON(imovel)).build();
        }catch (Exception e){
            return Response.serverError().entity(ExceptionUtils.getStackTrace(e)).build();
        }
    }

}
