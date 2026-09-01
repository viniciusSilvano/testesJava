package br.com.testeJava.entity.testeTransacaoConcorrentes;

import br.com.testeJava.entity.IEntidade;

import javax.persistence.*;

@Entity
@PersistenceUnit(name = "javaTesteProject")
@Table(name = "TB_CIB")
public class Cib implements IEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CIB")
    private Long id;

    @Column(name = "NUMERO_CIB")
    private String numeroCib;

    @Column(name = "SITUACAO_CIB")
    private String situacaoCib;

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
