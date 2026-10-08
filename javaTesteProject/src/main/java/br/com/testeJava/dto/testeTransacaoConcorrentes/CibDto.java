package br.com.testeJava.dto.testeTransacaoConcorrentes;

import br.com.testeJava.entity.testeTransacaoConcorrentes.Cib;

public class CibDto {
    private Long id;
    private String numeroCib;
    private String situacaoCib;

    public CibDto(Cib cib) {
        this.setId(cib.getId());
        this.setNumeroCib(cib.getNumeroCib());
        this.setSituacaoCib(cib.getSituacaoCib());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCib() {
        return numeroCib;
    }

    public void setNumeroCib(String numeroCib) {
        this.numeroCib = numeroCib;
    }

    public String getSituacaoCib() {
        return situacaoCib;
    }

    public void setSituacaoCib(String situacaoCib) {
        this.situacaoCib = situacaoCib;
    }
}
