package br.com.testeJava.dto.testeTransacaoConcorrentes;

import br.com.testeJava.entity.testeTransacaoConcorrentes.GrupoHistorico;
import br.com.testeJava.entity.testeTransacaoConcorrentes.HistoricoCibCar;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDto {
    private Long id;
    private String mensagem;
    private String situacaoCar;
    private String situacaoCib;
    private boolean irregular;

    public HistoricoDto(HistoricoCibCar historicoCibCar) {
        this.setId(historicoCibCar.getId());
        this.setMensagem(historicoCibCar.getMensagem());
        this.setSituacaoCar(historicoCibCar.getSituacaoCar());
        this.setSituacaoCib(historicoCibCar.getSituacaoCib());
        this.setIrregular(historicoCibCar.isIrregular());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getSituacaoCar() {
        return situacaoCar;
    }

    public void setSituacaoCar(String situacaoCar) {
        this.situacaoCar = situacaoCar;
    }

    public String getSituacaoCib() {
        return situacaoCib;
    }

    public void setSituacaoCib(String situacaoCib) {
        this.situacaoCib = situacaoCib;
    }

    public boolean isIrregular() {
        return irregular;
    }

    public void setIrregular(boolean irregular) {
        this.irregular = irregular;
    }
}
