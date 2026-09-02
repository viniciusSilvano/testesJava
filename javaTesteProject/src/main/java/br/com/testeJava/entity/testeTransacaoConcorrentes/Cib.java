package br.com.testeJava.entity.testeTransacaoConcorrentes;

import br.com.testeJava.entity.IEntidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name = "ID_IMOVEL")
    @JsonIgnore
    private Imovel imovel;

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

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cib)) return false;

        Cib cib = (Cib) o;
        return id != null && Objects.equals(id, cib.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
