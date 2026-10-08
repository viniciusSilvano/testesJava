package br.com.testeJava.dto.testeTransacaoConcorrentes;

import br.com.testeJava.entity.testeTransacaoConcorrentes.Cidade;
import br.com.testeJava.entity.testeTransacaoConcorrentes.GrupoHistorico;
import br.com.testeJava.entity.testeTransacaoConcorrentes.Imovel;

import java.util.ArrayList;
import java.util.List;

public class ImovelRespostaDto {

    private Long id;
    private String nome;
    private List<CibDto> cibs = new ArrayList<>();
    private List<GrupoHistoricoDto> grupos = new ArrayList<>();
    private String car;
    private Cidade cidade;
    private String situacaoCar;

    public ImovelRespostaDto(Imovel imovel) {
        this.setId(imovel.getId());
        this.setNome(imovel.getNome());
        this.setCar(imovel.getCar());
        this.setCidade(imovel.getCidade());
        this.setSituacaoCar(imovel.getSituacaoCar());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CibDto> getCibs() {
        return cibs;
    }

    public void setCibs(List<CibDto> cibs) {
        this.cibs = cibs;
    }

    public List<GrupoHistoricoDto> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoHistoricoDto> grupos) {
        this.grupos = grupos;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getSituacaoCar() {
        return situacaoCar;
    }

    public void setSituacaoCar(String situacaoCar) {
        this.situacaoCar = situacaoCar;
    }
}
