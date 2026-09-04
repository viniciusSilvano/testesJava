package br.com.testeJava.dao.testeTransacaoConcorrentes;


import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.HistoricoCibCarDAOQualifier;
import br.com.testeJava.entity.testeTransacaoConcorrentes.HistoricoCibCar;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@HistoricoCibCarDAOQualifier
@Stateless
public class HistoricoCibCarDAO extends BaseDAO<HistoricoCibCar> {

    public List<HistoricoCibCar> listarHistoricosCarCibComFilhos() {
        TypedQuery<HistoricoCibCar> query = getEntityManager().createQuery("SELECT h FROM HistoricoCibCar h JOIN FETCH h.historicosCibs ",HistoricoCibCar.class);
        return query.getResultList();
    }

    public List<HistoricoCibCar> listarHistoricosCarCibComPai () {
        TypedQuery<HistoricoCibCar> query = getEntityManager().createQuery("SELECT h FROM HistoricoCibCar h JOIN FETCH h.historicoCar ",HistoricoCibCar.class);
        return query.getResultList();
    }

    public List<HistoricoCibCar> listarHistoricosCarCib () {
        TypedQuery<HistoricoCibCar> query = getEntityManager().createQuery("SELECT h FROM HistoricoCibCar h ",HistoricoCibCar.class);
        return query.getResultList();
    }

    public List<HistoricoCibCar> listarHistoricosCarCibSemPai(){
        TypedQuery<HistoricoCibCar> query = getEntityManager().createQuery("SELECT h FROM HistoricoCibCar h WHERE h.historicoCar IS NULL ",HistoricoCibCar.class);
        return query.getResultList();
    }
}
