package br.com.testeJava.dto.testeTransacaoConcorrentes;

import br.com.testeJava.entity.testeTransacaoConcorrentes.Cib;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;

import java.util.ArrayList;
import java.util.List;

public class ImovelChecarAlteracaoDto {

    public ImovelChecarAlteracaoDto(Imovel imovel) {
        this.cibs = new ArrayList<>(imovel.getCibs());
        this.car = new String(imovel.getCar());
    }

    private List<Cib> cibs;

    private String car;

    public List<Cib> getCibs() {
        return cibs;
    }

    public void setCibs(List<Cib> cibs) {
        this.cibs = cibs;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
