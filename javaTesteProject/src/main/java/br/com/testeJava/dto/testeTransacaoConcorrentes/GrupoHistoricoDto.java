package br.com.testeJava.dto.testeTransacaoConcorrentes;

import br.com.testeJava.entity.testeTransacaoConcorrentes.GrupoHistorico;
import br.com.testeJava.entity.testeTransacaoConcorrentes.HistoricoCibCar;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

public class GrupoHistoricoDto {
    private Long id;
    private List<HistoricoDto> historicos;

    public GrupoHistoricoDto(GrupoHistorico grupoHistorico) {
        setId(grupoHistorico.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<HistoricoDto> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<HistoricoDto> historicos) {
        this.historicos = historicos;
    }
}
