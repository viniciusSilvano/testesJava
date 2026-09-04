package br.com.testeJava.bo.testeTransacaoConcorrentes;

import br.com.testeJava.bo.BaseService;
import br.com.testeJava.dao.BaseDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.HistoricoCibCarDAO;
import br.com.testeJava.dao.testeTransacaoConcorrentes.qualifier.HistoricoCibCarDAOQualifier;
import br.com.testeJava.entity.testeTransacaoConcorrentes.HistoricoCibCar;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Stateless
public class HistoricoCibCarBo extends BaseService<HistoricoCibCar> {

    @Inject
    @HistoricoCibCarDAOQualifier
    private HistoricoCibCarDAO historicoCibCarDAO;

    @Override
    protected BaseDAO<HistoricoCibCar> getDAO() {
        return historicoCibCarDAO;
    }

    public void adicionarHistoricoPaiEFilho() {
        HistoricoCibCar historicoCar = new HistoricoCibCar();
        HistoricoCibCar historicoCib1 = new HistoricoCibCar();
        HistoricoCibCar historicoCib2 = new HistoricoCibCar();
        HistoricoCibCar historicoCib3 = new HistoricoCibCar();

        historicoCar.setSituacaoCar("situação car");
        historicoCar.setMensagem("teste car");
        historicoCib1.setMensagem("teste cib 1");
        historicoCib1.setSituacaoCib("situação cib 1");
        historicoCib1.setHistoricoCar(historicoCar);
        historicoCib2.setMensagem("teste cib 2");
        historicoCib2.setSituacaoCib("situação cib 2");
        historicoCib2.setHistoricoCar(historicoCar);
        historicoCar.setHistoricosCibs(Arrays.asList(historicoCib1, historicoCib2));

        historicoCibCarDAO.saveEntityManager(
                historicoCar
        );

        historicoCibCarDAO.flush();

        historicoCib3.setMensagem("Teste cib sem pai");
        historicoCib3.setSituacaoCib("situação cib 3");

        historicoCibCarDAO.saveEntityManager(historicoCib3);

        historicoCibCarDAO.flush();
    }

    public List<HistoricoCibCar> listarHistoricosCarComFilhos() {
        List<HistoricoCibCar> historicoCibCarsComFilhos = this.historicoCibCarDAO.listarHistoricosCarCibComFilhos();
        return historicoCibCarsComFilhos;
    }

    public List<HistoricoCibCar> listarHistoricosCarComPai() {
        List<HistoricoCibCar> historicoCibCarsComPai = this.historicoCibCarDAO.listarHistoricosCarCibComPai();
        return historicoCibCarsComPai;
    }

    public List<HistoricoCibCar> listarHistoricosCar() {
        List<HistoricoCibCar> historicoCibCars = this.historicoCibCarDAO.listarHistoricosCarCib();
        return historicoCibCars;
    }

    public List<HistoricoCibCar> listarHistoricosCarCibSemPai(){
        List<HistoricoCibCar> historicoCibCars = this.historicoCibCarDAO.listarHistoricosCarCibSemPai();
        return historicoCibCars;
    }

    public void verificarSeHistoricoEhIrregular(){
        List<HistoricoCibCar> historicos = listarHistoricosCarCibSemPai();
        for(HistoricoCibCar historicoCibCar : historicos){
            String car = historicoCibCar.getSituacaoCar();
            String cib = historicoCibCar.getSituacaoCib();

            if(historicoCibCar.getHistoricosCibs() != null && !historicoCibCar.getHistoricosCibs().isEmpty()){
                boolean carInvalido = regraCarInvalido(historicoCibCar.getSituacaoCar());
                boolean cibInvalido = historicoCibCar.getHistoricosCibs().stream().anyMatch(historicoCib ->
                        regraCibInvalido(historicoCib.getSituacaoCib())
                );

            }

            boolean carInvalido = regraCarInvalido(car);
            boolean cibInvalido = regraCibInvalido(cib);

            historicoCibCar.setIrregular(carInvalido || cibInvalido);
        }
    }

    private static boolean regraCibInvalido(String cib) {
        return cib != null;
    }

    private static boolean regraCarInvalido(String car) {
        return car != null && !car.equals("nao");
    }
}
