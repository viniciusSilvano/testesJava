package br.com.testeJava.dao.testeTransacaoConcorrentes;

import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.ImovelDAOQualifier;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Cib;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@ImovelDAOQualifier
@Stateless
public class ImovelDAO extends BaseDAO<Imovel> {


    public List<Imovel> getImovelComCibs() {
        TypedQuery<Imovel> query = this.getEntityManager()
                .createQuery(
                        "SELECT DISTINCT imovel FROM Imovel imovel JOIN FETCH imovel.cibs",
                        Imovel.class
                );

        return query.getResultList();
    }

    public Long getContagemImoveis() {
        TypedQuery<Long> query = this.getEntityManager()
                .createQuery(
                        "SELECT count(imovel) FROM Imovel imovel ",
                        Long.class
                );

        return query.getSingleResult();
    }

    public void removeAll() {
        Query queryHistorico = this.getEntityManager().createQuery("DELETE FROM HistoricoCibCar historico ");
        queryHistorico.executeUpdate();

        Query queryGrupoHistorico = this.getEntityManager().createQuery("DELETE FROM GrupoHistorico grupoHistorico ");
        queryGrupoHistorico.executeUpdate();

        Query queryCib = this.getEntityManager().createQuery("DELETE FROM Cib cib ");
        queryCib.executeUpdate();

        Query query = this.getEntityManager().createQuery("DELETE FROM Imovel imovel ");
        query.executeUpdate();
    }

    public List<Cib> getCibs() {
        TypedQuery<Cib> query = this.getEntityManager()
                .createQuery(
                        "SELECT DISTINCT cib FROM Cib cib ",
                        Cib.class
                );

        return query.getResultList();
    }

    public void atualizarCibsBulkUpdate(Cib cib) {
        String jpql = "UPDATE Cib SET situacaoCib = :situacaoCib WHERE id = :idCib ";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("idCib",cib.getId());
        query.setParameter("situacaoCib",cib.getSituacaoCib());
        query.executeUpdate();
    }
}
