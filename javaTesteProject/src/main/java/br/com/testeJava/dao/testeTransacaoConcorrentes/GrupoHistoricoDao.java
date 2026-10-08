package br.com.testeJava.dao.testeTransacaoConcorrentes;

import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.GrupoHistoricoDAOQualifier;
import br.com.testeJava.entity.testeTransacaoConcorrentes.GrupoHistorico;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@GrupoHistoricoDAOQualifier
@Stateless
public class GrupoHistoricoDao extends BaseDAO<GrupoHistorico> {

    public List<GrupoHistorico> recuperarPorImovel(Long idImovel){
        TypedQuery<GrupoHistorico> query = getEntityManager().createQuery(
                "SELECT DISTINCT grupoHistorico FROM GrupoHistorico grupoHistorico WHERE grupoHistorico.imovel.id = :idImovel "
        ,GrupoHistorico.class);

        query.setParameter("idImovel", idImovel );

        return query.getResultList();
    }
}
